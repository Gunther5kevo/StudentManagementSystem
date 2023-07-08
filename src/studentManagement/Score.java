package studentManagement;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Score {
       private int cat;
       private int exam;
       private int scoreId; // id for this score in the database

       // constructor with arguments for cat and exam scores
       public Score(int cat, int exam) {
          this.cat = cat;
          this.exam = exam;
       }

       // Getter and Setter methods for all instance variables

       public int getCat() {
          return cat;
       }

       public void setCat(int cat) {
          this.cat = cat;
       }

       public int getExam() {
          return exam;
       }

       public void setExam(int exam) {
          this.exam = exam;
       }

       public int getTotalScore() {
          return this.cat + this.exam;
       }

       public String getGrade() {
          int totalScore = this.cat + this.exam;
          if (totalScore >= 70) {
             return "A";
          } else if (totalScore >= 60) {
             return "B";
          } else if (totalScore >= 50) {
             return "C";
          } else if (totalScore >= 40) {
             return "D";
          } else {
             return "F";
          }
       }

       // methods to store and retrieve data using JDBC

       public void saveScoreToDB() {
          try {
             Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ADMIN\\eclipse-workspace\\studentdb.db");
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO scores (cat_score, exam_score, grade) VALUES (?, ?, ?)");
             stmt.setInt(1, this.cat);
             stmt.setInt(2, this.exam);
             stmt.setString(3, this.getGrade());
             int rowsInserted = stmt.executeUpdate();
             if (rowsInserted > 0) {
                System.out.println("Score saved to database!");
             }
             ResultSet rs = stmt.getGeneratedKeys();
             if (rs.next()) {
                this.scoreId = rs.getInt(1);
             }
          } catch (SQLException ex) {
             ex.printStackTrace();
          }
       }

       public static Score getScoreFromDB(int scoreId) {
          try {
             Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/scores_db", "username", "password");
             PreparedStatement stmt = conn.prepareStatement("SELECT cat_score, exam_score FROM scores WHERE score_id = ?");
             stmt.setInt(1, scoreId);
             ResultSet rs = stmt.executeQuery();
             if (rs.next()) {
                Score score = new Score(rs.getInt("cat_score"), rs.getInt("exam_score"));
                score.scoreId = scoreId;
                return score;
             }
          } catch (SQLException ex) {
             ex.printStackTrace();
          }
          return null;
       }

       // main method for testing
       public static void main(String[] args) {
          Score score1 = new Score(80, 90);
          score1.saveScoreToDB();
          Score score2 = Score.getScoreFromDB(score1.scoreId);
          System.out.println("Score retrieved from database: cat=" + score2.getCat() + ", exam=" + score2.getExam() + ", grade=" + score2.getGrade());
       }
}