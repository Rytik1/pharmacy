package by.onlinepharmacy.command;

import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.CommandException;
import by.onlinepharmacy.logic.ImplLogic.AdministratorLogic;
import by.onlinepharmacy.resource.ConfigurationManager;
import by.onlinepharmacy.util.date.NavigationType;

import java.util.List;


public class StatisticCommand extends AbstractCommand{
    private static final int COUNT_BUYING = 0;
    private static final int BEST_MEDICAMENT = 1;
    private static final int COUNT_USER = 2;

    //get statistic for administrtor
    @Override
    public RequestResult execute() throws CommandException {
        String page = ConfigurationManager.getProperty("path.page.administrator");

        AdministratorLogic administratorLogic=new AdministratorLogic();
        try {
            List<String> list=administratorLogic.getStatistic();
            int countBuying= Integer.parseInt(list.get(COUNT_BUYING));
            String bestMedicament=  list.get(BEST_MEDICAMENT) ;
            int countRegistrUser= Integer.parseInt(list.get(COUNT_USER));

            getContent().setSessionAttribute("countBuying",countBuying);
            getContent().setSessionAttribute("bestMedicament",bestMedicament);
            getContent().setSessionAttribute("countRegistrUser",countRegistrUser);
            getContent().setSessionAttribute("state","true");

        } catch (BusinessLogicException e) {
            throw new CommandException("Problem update medicament command", e);
        }
        return new RequestResult(page, NavigationType.FORWARD);

    }

}
