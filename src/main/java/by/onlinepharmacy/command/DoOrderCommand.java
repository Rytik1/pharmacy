package by.onlinepharmacy.command;

import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.entity.Medicament;
import by.onlinepharmacy.entity.Recipe;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.CommandException;
import by.onlinepharmacy.logic.ImplLogic.CommonLogic;
import by.onlinepharmacy.logic.ImplLogic.OrderLogic;
import by.onlinepharmacy.resource.ConfigurationManager;
import by.onlinepharmacy.resource.MessageManager;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class DoOrderCommand extends AbstractCommand {
    private Lock lock = new ReentrantLock();
    private static final String LOGIN_ATRIBUTE = "user";
    private static final String COUNT_ORDER_PARAM = "countOrder";
    private static final String RECIPE_REQUIR_PARAM = "recipeRequired";
    private static final String MEDICAMENT_ID_PARAM = "id";
    private static final String COMMAND = "FIND_MEDICAMENT";

    //do order medicament method
    public RequestResult execute() {

        double countStock = 0;
        String page = ConfigurationManager.getProperty("path.page.main");
        String login = getContent().getSessionAttribute(LOGIN_ATRIBUTE);
        boolean recipeReuired = Boolean.parseBoolean((getContent().getRequestParameter(RECIPE_REQUIR_PARAM)));
        int medicamentID = Integer.parseInt(getContent().getRequestParameter(MEDICAMENT_ID_PARAM));
        String count = getContent().getRequestParameter(COUNT_ORDER_PARAM);

        OrderLogic orderLogic = new OrderLogic();
        List<Medicament> medicamentList = new ArrayList<>();
        try {
            //protect from one time add in basket (count=2 one user 2 and second user 2 ->-2)
            lock.lock();
            //actual count in pharmacy
            countStock = orderLogic.getActualCountMedicament(medicamentID);

            if (!orderLogic.isCountValid(String.valueOf(count))) {
                getContent().setSessionAttribute("invalideСount", MessageManager.getProperty("message.invalideСount"));
                getMedicamentList();

            } else {
                double countOrder = Double.parseDouble(count);
                double countResult = countStock - countOrder;
                if (countResult < 0) {
                    getContent().setSessionAttribute("notCountMed", MessageManager.getProperty("message.notCountMed"));
                    getMedicamentList();
                } else {
                    Optional<List<Recipe>> recipeList = orderLogic.checkRecipe(login, medicamentID);

                    if (recipeReuired) {
                        if (!recipeList.get().isEmpty()) {
                            getContent().setSessionAttribute("confirmAdd", MessageManager.getProperty("message.confirmAdd"));
                            orderLogic.addNewOrder(medicamentID, countOrder, login, countResult);
                            getMedicamentList();
                        } else {
                            getContent().setSessionAttribute("notRecipe", MessageManager.getProperty("message.notRecipe"));
                            getMedicamentList();
                        }
                    } else {
                        orderLogic.addNewOrder(medicamentID, countOrder, login, countResult);
                        getContent().setSessionAttribute("orderInBasket", MessageManager.getProperty("message.orderInBasket"));
                        getMedicamentList();
                    }
                }
            }
        } catch (BusinessLogicException e) {
            throw new CommandException("Problem with do order command: ", e);

        } finally {
            lock.unlock();

            // cose redirect cose protected from f5
            return new RequestResult(page, NavigationType.REDIRECT);
        }

    }

    //check true medicament list (show all medicament or short list medicament)
    //this attr from FindMedicamentCommand
    public void getMedicamentList() {
        String name = getContent().getSessionAttribute("findName");
        String command = getContent().getSessionAttribute("findCommand");

        CommonLogic commonLogic = new CommonLogic();
        List<Medicament> medicamentList = null;

        try {
            if (!command.equalsIgnoreCase(COMMAND)) {
                medicamentList = commonLogic.selectAllMedicament();
            } else {
                medicamentList = commonLogic.findMedicament(name);
            }
        } catch (BusinessLogicException e) {
            e.printStackTrace();
        }
        if (medicamentList.size() != 0) {
            //session cose redirect (protected from f5)
            getContent().setSessionAttribute("findmedicamentList", medicamentList);
        }

    }


}






