package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOBILE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.ParserUtil.OBJECT_TYPE_POSITION;
import static seedu.address.logic.parser.ParserUtil.removeDash;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.command.edit.EditAccommodationCommand;
import seedu.address.logic.command.edit.EditActivityCommand;
import seedu.address.logic.command.edit.EditCommand;
import seedu.address.logic.command.edit.EditDescriptor;
import seedu.address.logic.command.edit.EditFriendCommand;
import seedu.address.logic.command.edit.EditTravelPlanCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * Ensures that the type of edited object, index, tags are specified and valid
     *
     * @param args a String that had specified an edit command
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_IMPORTANCE, PREFIX_COST,
                PREFIX_LOCATION, PREFIX_DATETIME, PREFIX_PASSPORT, PREFIX_MOBILE, PREFIX_START, PREFIX_END);
        EditDescriptor checker = EditDescriptor.buildFromSource(argMultimap);

        String[] keywords = args.split(" ");
        String editType = removeDash(keywords[OBJECT_TYPE_POSITION], EditCommand.MESSAGE_USAGE);
        if (checker.wrongFieldEdited(editType)) {
            throw new ParseException(EditCommand.INVALID_FIELDS);
        }


        try {
            switch (editType) {

            case EditActivityCommand.COMMAND_WORD:
                return parseActivity(args);

            case EditAccommodationCommand.COMMAND_WORD:
                return parseAccommodation(args);

            case EditFriendCommand.COMMAND_WORD:
                return parseFriend(args);

            case EditTravelPlanCommand.COMMAND_WORD:
                return parseTravelPlan(args);

            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
            }

        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, e.getMessage()));
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }
    }

    EditActivityCommand parseActivity(String args) throws ParseException {

        String[] keywords = args.split(" ");


        Index index = ParserUtil.parseIndex(keywords[2]);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_IMPORTANCE, PREFIX_COST,
                PREFIX_LOCATION, PREFIX_DATETIME);
        EditDescriptor editDescriptor = EditDescriptor.buildFromSource(argMultimap);

        return new EditActivityCommand(index, editDescriptor);

    }

    EditAccommodationCommand parseAccommodation(String args) throws ParseException {

        String[] keywords = args.split(" ");
        Index index = ParserUtil.parseIndex(keywords[2]);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_COST, PREFIX_LOCATION,
                PREFIX_START, PREFIX_END);
        EditDescriptor editDescriptor = EditDescriptor.buildFromSource(argMultimap);

        return new EditAccommodationCommand(index, editDescriptor);

    }

    EditFriendCommand parseFriend(String args) throws ParseException {

        String[] keywords = args.split(" ");
        Index index = ParserUtil.parseIndex(keywords[2]);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PASSPORT,
                PREFIX_MOBILE);
        EditDescriptor editDescriptor = EditDescriptor.buildFromSource(argumentMultimap);

        return new EditFriendCommand(index, editDescriptor);

    }

    EditTravelPlanCommand parseTravelPlan(String args) throws ParseException {

        String[] keywords = args.split(" ");
        Index index = ParserUtil.parseIndex(keywords[2]);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_START, PREFIX_END);
        EditDescriptor editDescriptor = EditDescriptor.buildFromSource(argMultimap);

        return new EditTravelPlanCommand(index, editDescriptor);

    }


}
