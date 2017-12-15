package by.onlinepharmacy.command;

import java.util.*;

import static by.onlinepharmacy.command.CommandType.*;


public class CommandMap {
    private EnumMap<CommandType, AbstractCommand> map = new EnumMap<>(CommandType.class);
    private static CommandMap instance = new CommandMap();

    private CommandMap() {
        map.put(LOGIN, new LoginCommand());
        map.put(LOGOUT, new LogoutCommand());
        map.put(REGISTRATION, new RegistrationCommand());
        map.put(ADD_MEDICAMENT, new AddMedicamentCommand());
        map.put(ALL_MEDICAMENT, new ShowAllMedicament());
        map.put(FIND_MEDICAMENT, new FindMedicamentCommand());
        map.put(GO_UPDATE, new GoToUpdateMedicamentCommand());
        map.put(UPDATE_MEDICAMENT, new UpdateMedicamentCommand());
        map.put(DELETE_MEDICAMENT, new DeleteMedicamentCommand());
        map.put(GO_TO_DELETE_MEDICAMENT, new GoToDeleteMedicamentCommand());
        map.put(SHOW_ALL_USER, new ShowAllUserCommand());
        map.put(GO_TO_DELETE_USER, new GoToDeleteUserCommand());
        map.put(FIND_USER_BY_LOGIN, new FindUserCommand());
        map.put(DELETE_USER, new DeleteUserCommand());
        map.put(GO_TO_UPDATE_USER, new GoToUpdateUserCommand());
        map.put(UPDATE_USER, new UserUpdateInformationCommand());
        map.put(DO_ORDER, new DoOrderCommand());
        map.put(GO_TO_BASKET, new BasketCommand());
        map.put(DELETE_ORDER, new DeleteOrderCommand());
        map.put(CONFIRM_ORDER, new ConfirmOrderCommand());
        map.put(GO_TO_REGISTRATION, new GoToRegistrationCommand());
        map.put(SHOW_RECIPE, new ShowUserRecipeCommand());
        map.put(HOME, new HomeCommand());
        map.put(GO_TO_ADD_MEDICAMENT, new GoToAddMedicamentCommand());
        map.put(HOME_ADMINISTRATOR, new HomeAdministratorCommand());
        map.put(GAME, new GameCommand());
        map.put(HISTORY, new HistoryBuyingCommand());
        map.put(GAMESALE, new GameSaleCommand());
        map.put(CHANGE_FOTO, new ChangeFotoCommand());
        map.put(REQUEST_INFORMATION_COUNT_MEDICAMENT, new RequestInformationMedCountCommand());
        map.put(GAME_CHECK_NUMBER, new GameCheckNumberCommand());
        map.put(CHANGE_USER_PASSWORD, new ChangeUserPassCommand());
        map.put(STATISTIC, new StatisticCommand());

    }

    public static CommandMap getInstance() {
        return instance;
    }

    EnumMap<CommandType, AbstractCommand> get() {
        return map;
    }

    //define command from FORM and return Obj with need command class
    public static AbstractCommand defineCommandType(String comamnd) {
        List<AbstractCommand> list = new ArrayList<>();
        CommandType commandType = CommandType.valueOf(comamnd);
        getInstance().map.entrySet().stream().filter(o -> o.getKey().equals(commandType)).forEach(o -> list.add(o.getValue()));
        return list.get(0);
    }

}
