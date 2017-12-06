package ru.naumen.sd40.log.parser;

import org.junit.Assert;
import org.junit.Test;
import ru.naumen.sd40.log.parser.Storage.ActionDoneStorage;

public class ActionDoneParserTest {

    @Test
    public void mustParseAddAction() {
        //given
        ActionDoneParser parser = new ActionDoneParser();
        ActionDoneStorage storage = new ActionDoneStorage();

        //when
        parser.parseLine("Done(10): AddObjectAction", storage);

        //then
        Assert.assertEquals(1, storage.addObjectActions);
    }

    @Test
    public void mustParseFormActions() {
        //given
        ActionDoneParser parser = new ActionDoneParser();
        ActionDoneStorage storage = new ActionDoneStorage();

        //when
        parser.parseLine("Done(10): GetFormAction", storage);
        parser.parseLine("Done(1): GetAddFormContextDataAction", storage);

        //then
        Assert.assertEquals(2, storage.getFormActions);
    }

    @Test
    public void mustParseEditObject() {
        //given
        ActionDoneParser parser=  new ActionDoneParser();
        ActionDoneStorage storage = new ActionDoneStorage();

        //when
        parser.parseLine("Done(10): EditObjectAction", storage);

        //then
        Assert.assertEquals(1, storage.editObjectsActions);
    }

    @Test
    public void mustParseSearchObject(){
        //given
        ActionDoneParser parser = new ActionDoneParser();
        ActionDoneStorage storage = new ActionDoneStorage();

        //when
        parser.parseLine("Done(10): GetPossibleAgreementsChildsSearchAction", storage);
        parser.parseLine("Done(10): TreeSearchAction", storage);
        parser.parseLine("Done(10): GetSearchResultAction", storage);
        parser.parseLine("Done(10): GetSimpleSearchResultsAction", storage);
        parser.parseLine("Done(10): SimpleSearchAction", storage);
        parser.parseLine("Done(10): ExtendedSearchByStringAction", storage);
        parser.parseLine("Done(10): ExtendedSearchByFilterAction", storage);

        //then
        Assert.assertEquals(7, storage.searchActions);
    }

    @Test
    public void mustParseGetList(){
        //given:
        ActionDoneParser parser=  new ActionDoneParser();
        ActionDoneStorage storage = new ActionDoneStorage();

        //when:
        parser.parseLine("Done(10): GetDtObjectListAction", storage);
        parser.parseLine("Done(10): GetPossibleCaseListValueAction", storage);
        parser.parseLine("Done(10): GetPossibleAgreementsTreeListActions", storage);
        parser.parseLine("Done(10): GetCountForObjectListAction", storage);
        parser.parseLine("Done(10): GetDataForObjectListAction", storage);
        parser.parseLine("Done(10): GetPossibleAgreementsListActions", storage);
        parser.parseLine("Done(10): GetDtObjectForRelObjListAction", storage);

        //then:
        Assert.assertEquals(7, storage.getListActions);
    }

    @Test
    public void mustParseComment(){
        //given:
        ActionDoneParser parser=  new ActionDoneParser();
        ActionDoneStorage storage = new ActionDoneStorage();

        //when:
        parser.parseLine("Done(10): EditCommentAction", storage);
        parser.parseLine("Done(10): ChangeResponsibleWithAddCommentAction", storage);
        parser.parseLine("Done(10): ShowMoreCommentAttrsAction", storage);
        parser.parseLine("Done(10): CheckObjectsExceedsCommentsAmountAction", storage);
        parser.parseLine("Done(10): GetAddCommentPermissionAction", storage);
        parser.parseLine("Done(10): GetCommentDtObjectTemplateAction", storage);

        //then:
        Assert.assertEquals(6, storage.commentActions);
    }

    @Test
    public void mustParseDtObject(){
        //given:
        ActionDoneParser parser=  new ActionDoneParser();
        ActionDoneStorage storage = new ActionDoneStorage();

        //when:
        parser.parseLine("Done(10): GetVisibleDtObjectAction", storage);
        parser.parseLine("Done(10): GetDtObjectsAction", storage);
        parser.parseLine("Done(10): GetDtObjectTreeSelectionStateAction", storage);
        parser.parseLine("Done(10): AbstractGetDtObjectTemplateAction", storage);
        parser.parseLine("Done(10): GetDtObjectTemplateAction", storage);

        //then:
        Assert.assertEquals(5, storage.getDtObjectActions);
    }

}
