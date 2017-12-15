package by.onlinepharmacy.command;

import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.entity.Medicament;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.CommandException;
import by.onlinepharmacy.logic.ImplLogic.AdministratorLogic;
import by.onlinepharmacy.logic.ImplLogic.OrderLogic;
import by.onlinepharmacy.util.mail.MailUtil;
import by.onlinepharmacy.resource.ConfigurationManager;
import by.onlinepharmacy.resource.MessageManager;

import java.util.List;


public class UpdateMedicamentCommand extends AbstractCommand {
    private static final String ID_PARAM = "id";
    private static final String NAME_PARAM = "name";
    private static final String DOSAGE_PARAM = "dosage";
    private static final String RECIPE_PARAM = "recipe_required";
    private static final String PRICE_PARAM = "price";
    private static final String COUNT_PARAM = "amount_in_stock";
    private static final String COUNTRY_PARAM = "country";

//update medicament
    @Override
    public RequestResult execute() throws CommandException {

        String page = ConfigurationManager.getProperty("path.page.update_medicament");
        int id = Integer.parseInt(getContent().getRequestParameter(ID_PARAM));
        String name = getContent().getRequestParameter(NAME_PARAM);
        String dosage = getContent().getRequestParameter(DOSAGE_PARAM);
        boolean recipeRequired = Boolean.parseBoolean(getContent().getRequestParameter(RECIPE_PARAM));
        String price = getContent().getRequestParameter(PRICE_PARAM);
        String amountInStock = getContent().getRequestParameter(COUNT_PARAM);
        String country = getContent().getRequestParameter(COUNTRY_PARAM);
        AdministratorLogic administratorLogic = new AdministratorLogic();
        OrderLogic orderLogic = new OrderLogic();
        try {
            if (!administratorLogic.isCountValid(amountInStock)) {
                getContent().setRequestAttribute("countwrong", MessageManager.getProperty("message.countwrong"));
            } else if (!administratorLogic.isPriceValid(price)) {
                getContent().setRequestAttribute("pricewrong", MessageManager.getProperty("message.pricewrong"));
            } else if (!administratorLogic.isCountryValid(country)) {
                getContent().setRequestAttribute("countrywrong", MessageManager.getProperty("message.countrywrong"));

            } else {
                double priceForLogic = Double.parseDouble(price);
                double amountInStockForLogic = Double.parseDouble(amountInStock);
                Medicament medicament=administratorLogic.updateMedicament(id, name, dosage, amountInStockForLogic, country, priceForLogic, recipeRequired);
                getContent().setRequestAttribute("update_medicament", MessageManager.getProperty("message.update_medicament"));
                getContent().setRequestAttribute("name", name);
                getContent().setRequestAttribute("medicament", medicament);

                //send email with info if count med ufter update will >0
                if (amountInStockForLogic > 0) {
                    List<String> list = orderLogic.selectEmailByMedId(id);
                    list.stream().forEach(Email -> MailUtil.sendEmail(name, Email));
                    orderLogic.DeleteUserReqByMedId(id);
                }
            }
        } catch (BusinessLogicException e) {
            throw new CommandException("Problem update medicament command", e);
        }

        return new RequestResult(page, NavigationType.FORWARD);

    }

}


