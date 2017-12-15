package by.onlinepharmacy.command;

import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.CommandException;
import by.onlinepharmacy.logic.ImplLogic.AdministratorLogic;
import by.onlinepharmacy.resource.ConfigurationManager;
import by.onlinepharmacy.resource.MessageManager;


public class AddMedicamentCommand extends AbstractCommand {
    private static final String NAME_PARAM = "name";
    private static final String DOSAGE_PARAM = "dosage";
    private static final String RECIPE_PARAM = "recipe_required";
    private static final String PRICE_PARAM = "price";
    private static final String COUNT_PARAM = "amount_in_stock";
    private static final String COUNTRY_PARAM = "country";

    //add new medicament method
    @Override
    public RequestResult execute() throws CommandException {

        String page = ConfigurationManager.getProperty("path.page.addMedicament");
        String name = getContent().getRequestParameter(NAME_PARAM);
        String dosage = getContent().getRequestParameter(DOSAGE_PARAM);
        boolean recipeRequired = Boolean.parseBoolean(getContent().getRequestParameter(RECIPE_PARAM));
        String price = getContent().getRequestParameter(PRICE_PARAM);
        String amountInStock = getContent().getRequestParameter(COUNT_PARAM);
        String country = getContent().getRequestParameter(COUNTRY_PARAM);
        String imageName = getContent().getRequestPartsName().get(0);
        String imagePath = "/resources/image/" + imageName;
        AdministratorLogic administratorLogic = new AdministratorLogic();


        try {
            if (!administratorLogic.isCountValid(amountInStock)) {
                getContent().setRequestAttribute("countwrong", MessageManager.getProperty("message.countwrong"));
            } else if (!administratorLogic.isPriceValid(price)) {
                getContent().setRequestAttribute("pricewrong", MessageManager.getProperty("message.pricewrong"));
            } else if (!administratorLogic.isCountryValid(country)) {
                getContent().setRequestAttribute("countrywrong", MessageManager.getProperty("message.countrywrong"));
            } else if (administratorLogic.checkUniquePeparat(name, dosage)) {
                getContent().setRequestAttribute("notUniquePreparat", MessageManager.getProperty("message.notUniquePreparat"));
            } else {

                double priceForLogic = Double.parseDouble(price);
                double amountInStockForLogic = Double.parseDouble(amountInStock);

                administratorLogic.addNewMedicament(name, dosage, amountInStockForLogic, country, priceForLogic, recipeRequired,imagePath );
                getContent().setRequestAttribute("NewMedicament", MessageManager.getProperty("message.newmedicament"));
                getContent().setRequestAttribute("name", name);
            }
        } catch (BusinessLogicException e) {
            throw new CommandException("Problem with add medicament command: ", e);
        }
        return new RequestResult(page, NavigationType.FORWARD);
    }


}