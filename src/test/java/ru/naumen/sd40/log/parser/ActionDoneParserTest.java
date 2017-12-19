package ru.naumen.sd40.log.parser;

import org.junit.Assert;
import org.junit.Test;
import ru.naumen.sd40.log.parser.DataParsers.ActionDoneParser;
import ru.naumen.sd40.log.parser.Storages.ActionDoneStorage;

public class ActionDoneParserTest {

    @Test
    public void mustParseAddAction() {
        //given
        ActionDoneParser parser = new ActionDoneParser();

        //when
        ActionDoneStorage storage = new ActionDoneStorage();
        parser.parseLine(storage,"Done(10): AddObjectAction");

        //then
        Assert.assertEquals(1, storage.addObjectActions);
    }

    @Test
    public void mustParseFormActions() {
        //given
        ActionDoneParser parser = new ActionDoneParser();
        ActionDoneStorage storage = new ActionDoneStorage();

        //when
        parser.parseLine(storage,"Done(10): GetFormAction");
        parser.parseLine(storage,"Done(1): GetAddFormContextDataAction");

        //then
        Assert.assertEquals(2, storage.getFormActions);
    }

    @Test
    public void mustParseEditObject() {
        //given
        ActionDoneParser parser=  new ActionDoneParser();
        ActionDoneStorage storage = new ActionDoneStorage();

        //when
        parser.parseLine(storage,"Done(10): EditObjectAction");

        //then
        Assert.assertEquals(1, storage.editObjectsActions);
    }

    @Test
    public void mustParseSearchObject(){
        //given
        ActionDoneParser parser = new ActionDoneParser();
        ActionDoneStorage storage = new ActionDoneStorage();

        //when
        parser.parseLine(storage,"Done(10): GetPossibleAgreementsChildsSearchAction");
        parser.parseLine(storage,"Done(10): TreeSearchAction");
        parser.parseLine(storage,"Done(10): GetSearchResultAction");
        parser.parseLine(storage,"Done(10): GetSimpleSearchResultsAction");
        parser.parseLine(storage,"Done(10): SimpleSearchAction");
        parser.parseLine(storage,"Done(10): ExtendedSearchByStringAction");
        parser.parseLine(storage,"Done(10): ExtendedSearchByFilterAction");

        //then
        Assert.assertEquals(7, storage.searchActions);
    }

    @Test
    public void mustParseGetList(){
        //given:
        ActionDoneParser parser=  new ActionDoneParser();
        ActionDoneStorage storage = new ActionDoneStorage();

        //when:
        parser.parseLine(storage,"Done(10): GetDtObjectListAction");
        parser.parseLine(storage,"Done(10): GetPossibleCaseListValueAction");
        parser.parseLine(storage,"Done(10): GetPossibleAgreementsTreeListActions");
        parser.parseLine(storage,"Done(10): GetCountForObjectListAction");
        parser.parseLine(storage,"Done(10): GetDataForObjectListAction");
        parser.parseLine(storage,"Done(10): GetPossibleAgreementsListActions");
        parser.parseLine(storage,"Done(10): GetDtObjectForRelObjListAction");

        //then:
        Assert.assertEquals(7, storage.getListActions);
    }

    @Test
    public void mustParseComment(){
        //given:
        ActionDoneParser parser=  new ActionDoneParser();
        ActionDoneStorage storage = new ActionDoneStorage();

        //when:
        parser.parseLine(storage,"Done(10): EditCommentAction");
        parser.parseLine(storage,"Done(10): ChangeResponsibleWithAddCommentAction");
        parser.parseLine(storage,"Done(10): ShowMoreCommentAttrsAction");
        parser.parseLine(storage,"Done(10): CheckObjectsExceedsCommentsAmountAction");
        parser.parseLine(storage,"Done(10): GetAddCommentPermissionAction");
        parser.parseLine(storage,"Done(10): GetCommentDtObjectTemplateAction");

        //then:
        Assert.assertEquals(6, storage.commentActions);
    }

    @Test
    public void mustParseDtObject(){
        //given:
        ActionDoneParser parser=  new ActionDoneParser();
        ActionDoneStorage storage = new ActionDoneStorage();

        //when:
        parser.parseLine(storage,"Done(10): GetVisibleDtObjectAction");
        parser.parseLine(storage,"Done(10): GetDtObjectsAction");
        parser.parseLine(storage,"Done(10): GetDtObjectTreeSelectionStateAction");
        parser.parseLine(storage,"Done(10): AbstractGetDtObjectTemplateAction");
        parser.parseLine(storage,"Done(10): GetDtObjectTemplateAction");

        //then:
        Assert.assertEquals(5, storage.getDtObjectActions);
    }

}
