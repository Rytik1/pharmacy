package by.onlinepharmacy.command;

import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.entity.Medicament;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.exception.CommandException;
import by.onlinepharmacy.logic.ImplLogic.AdministratorLogic;
import by.onlinepharmacy.resource.ConfigurationManager;
import by.onlinepharmacy.resource.MessageManager;


public class ChangeFotoCommand extends AbstractCommand {
    private static final String ID_PARAM = "id";
    private static final String PATH = "/resources/image/";

    //change foto medicament
    @Override
    public RequestResult execute() throws CommandException {

        String page = ConfigurationManager.getProperty("path.page.update_medicament");
        int id = Integer.parseInt(getContent().getRequestParameter(ID_PARAM));
         AdministratorLogic administratorLogic = new AdministratorLogic();
        String imageName = getContent().getRequestPartsName().get(0);
        String imagePath = PATH + imageName;
         try {
             administratorLogic.updateMedicamentFoto(id, imagePath);
             Medicament medicament = administratorLogic.getMedicamentByID(id);
           if(imagePath.equals(PATH)){
               getContent().setRequestAttribute("checkFoto", MessageManager.getProperty("message.checkFoto"));
               getContent().setRequestAttribute("medicament", medicament);
           }else if (medicament != null) {
                getContent().setRequestAttribute("medicament", medicament);
                getContent().setRequestAttribute("changeFoto", MessageManager.getProperty("message.changeFoto"));
            }

        } catch (BusinessLogicException e) {
            throw new CommandException("Problem update medicament command", e);
        }

        return new RequestResult(page, NavigationType.FORWARD);

    }

}


