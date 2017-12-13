package ru.naumen.sd40.log.parser.DataParsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import ru.naumen.sd40.log.parser.Interfaces.IDataParser;
import ru.naumen.sd40.log.parser.Storages.ActionDoneStorage;

@Service
public class ActionDoneParser implements IDataParser<ActionDoneStorage>
{

    Pattern doneRegEx = Pattern.compile("Done\\((\\d+)\\): ?(.*?Action)");


    public void parseLine(ActionDoneStorage storage, String line)
    {
        Matcher matcher = doneRegEx.matcher(line);

        if (matcher.find())
        {
            String actionInLowerCase = matcher.group(2).toLowerCase();
            if (storage.EXCLUDED_ACTIONS.contains(actionInLowerCase)){

                return;
            }

            storage.times.add(Integer.parseInt(matcher.group(1)));
            if (actionInLowerCase.equals("addobjectaction"))
            {
                storage.addObjectActions++;
            }
            else if (actionInLowerCase.equals("editobjectaction"))
            {
                storage.editObjectsActions++;
            }
            else if (actionInLowerCase.matches("(?i)[a-zA-Z]+comment[a-zA-Z]+"))
            {
                storage.commentActions++;
            }
            else if (!actionInLowerCase.contains("advlist")
                    && actionInLowerCase.matches("(?i)^([a-zA-Z]+|Get)[a-zA-Z]+List[a-zA-Z]+"))

            {
                storage.getListActions++;
            }
            else if (actionInLowerCase.matches("(?i)^([a-zA-Z]+|Get)[a-zA-Z]+Form[a-zA-Z]+"))
            {
                storage.getFormActions++;
            }
            else if (actionInLowerCase.matches("(?i)^([a-zA-Z]+|Get)[a-zA-Z]+DtObject[a-zA-Z]+"))
            {
                storage.getDtObjectActions++;
            }
            else if (actionInLowerCase.matches("(?i)[a-zA-Z]+search[a-zA-Z]+"))
            {
                storage.searchActions++;
            }

        }
    }
}
