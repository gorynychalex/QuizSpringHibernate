package ru.dvfu.mrcpk.develop.server.dao;

import java.sql.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dvfu.mrcpk.develop.server.model.Question;
import ru.dvfu.mrcpk.develop.server.model.QuestionInterface;
import ru.dvfu.mrcpk.develop.server.model.QuizInterface;
import ru.dvfu.mrcpk.develop.server.model.QuizTest;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class QuestionDAOSimpleJDBC implements QuestionDAOInterface {

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public QuestionInterface getById(Number id) {

        QuestionInterface questionTest = null;

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Quiz.Question WHERE ID=?");

            preparedStatement.setInt(1, (Integer) id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                questionTest = new Question(
//                resultSet.getInt("id"),
//                resultSet.getString("text")
                );
            }
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        } finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    throw new RuntimeException(e);
                }
            }
        }

        return questionTest;
    }

    public List<QuestionInterface> list() {
        return null;
    }

    public List<QuestionInterface> list(Number id) {
        return null;
    }

    public void add(Number quizId, QuestionInterface question) {

    }

    public void add(QuestionInterface question) {

    }

    public void update(QuestionInterface question) {

    }

    public void add(Number quizId, QuizInterface question) {

    }

    public void update(QuizInterface question) {

    }

    public void remove(Number id) {

    }
}
