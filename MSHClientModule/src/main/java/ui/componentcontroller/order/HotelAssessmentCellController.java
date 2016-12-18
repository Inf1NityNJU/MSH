package ui.componentcontroller.order;

import component.ratestarpane.RateStarPane;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import vo.AssessmentVO;
import vo.Assessment_HotelVO;

/**
 * Created by Sorumi on 16/12/17.
 */
public class HotelAssessmentCellController {

    @FXML
    private Label clientNameLabel;

    @FXML
    private RateStarPane scorePane;

    @FXML
    private Label scoreLabel;

    @FXML
    private Text commentText;

    @FXML
    private Label dateLabel;


    public void setAssessment(Assessment_HotelVO assessment) {
        clientNameLabel.setText(assessment.clientName);
        double score = (assessment.serviceScore
                + assessment.facilityScore
                + assessment.locationScore
                + assessment.healthScore);
        score = score / 4.0;
        scoreLabel.setText(String.format("%.1f", score) + " 分");
        scorePane.setScore((int)score);
        commentText.setText(assessment.comment);
        dateLabel.setText(assessment.date.toString());
    }

}
