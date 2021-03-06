package dcll.ctc.dcll.ctc.parser.gui;

import java.util.ArrayList;
import java.util.List;

import org.tsaap.questions.Answer;
import org.tsaap.questions.AnswerBlock;
import org.tsaap.questions.Question;

import dcll.ctc.dcll.ctc.parser.gui.model.CheckBoxAnswer;

/**
 * @author alpha oumar binta diallo
 */
@SuppressWarnings("serial")
public class MultipleChoicePanel extends QuestionJPanel {

    /**
     * a CheckBoxAnswer list.
     */
    private List<CheckBoxAnswer> checkboxList =
            new ArrayList<CheckBoxAnswer>();
    /**
     * constructor with an answer.
     * @param question the question to set
     */
    public MultipleChoicePanel(final Question question) {
        super(question);
        for (AnswerBlock answerBlock : getQuestion().getAnswerBlockList()) {
            for (Answer ans : answerBlock.getAnswerList()) {
                CheckBoxAnswer checkbox = new CheckBoxAnswer(ans);
                add(checkbox);
                checkboxList.add(checkbox);
            }
        }
    }

    @Override
    public float compute() {
        float credit = 0;
        boolean badAnswer = false;
        for (CheckBoxAnswer ans : checkboxList) {
            float creditans = ans.getCorrection();
            if (ans.isSelected()) {
                credit += creditans;
                if (!badAnswer && creditans <= 0) {
                badAnswer = true;
                }
            }
        }
        if (badAnswer) {
        credit = 0;
        }
        return credit;
    }

    @Override
    public void reset() {
        for (CheckBoxAnswer ans : checkboxList) {
            ans.resetCorrection();
        }
    }

}
