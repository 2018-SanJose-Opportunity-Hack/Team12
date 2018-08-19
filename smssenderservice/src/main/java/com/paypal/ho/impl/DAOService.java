package com.paypal.ho.impl;

import com.paypal.ho.dao.Conversation;
import com.paypal.ho.dao.Feedback;
import com.paypal.ho.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Service
public class DAOService {

    /**
     * convoID varchar(20),
     * scheduleId INT,
     * status int,
     * userId int
     */
    private final String INSERT_SQL = "INSERT INTO conversation(convoID,scheduleId,status,userId, createTime, "
            + "lastUpdateTime) values(?,?,?,?,?,?)";

    /**
     * id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     * timestamp long ,
     * questionId int,
     * userId int,
     * conversationId int,
     * phoneNumber varchar(20),
     * content varchar(200)
     */
    private final String INSERT_FEEDBACK_SQL =
            "INSERT INTO feedback(timestamp,questionId,userId,conversationId, phoneNumber, "
                    + "content) values(?,?,?,?,?,?)";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void selectAllUsers() {
        jdbcTemplate.query(
                "SELECT * FROM user WHERE name = ?", new Object[] { "test_qifu" },
                (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("name"), rs.getString("email"), rs
                        .getString("phonenumber"))
        ).forEach(user -> System.out.println(user.getEmail()));
    }

    public User queryUserByName(String phoneNumber) {
        List<User> lst = new ArrayList<>();
        jdbcTemplate.query(
                "SELECT * FROM user WHERE name = ?", new Object[] { phoneNumber },
                (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("name"), rs.getString("email"), rs
                        .getString("phonenumber"))
        ).forEach(user -> lst.add(user));
        return lst.get(0);
    }

    public boolean addConversaton(Conversation conversation) {
        KeyHolder holder = new GeneratedKeyHolder();
        final int rst = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, conversation.getConvoID());
                ps.setInt(2, conversation.getScheduleId());
                ps.setInt(3, conversation.getStatus());
                ps.setInt(4, conversation.getUserId());
                ps.setLong(5, conversation.getCreateTime());
                ps.setLong(6, conversation.getLastUpdateTime());
                return ps;
            }
        }, holder);
        return rst == 0;
    }

    public boolean addFeedback(Feedback feedback) {
        KeyHolder holder = new GeneratedKeyHolder();
        final int rst = jdbcTemplate.update(new PreparedStatementCreator() {
            //timestamp,questionId,userId,conversationId, phoneNumber. content
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection
                        .prepareStatement(INSERT_FEEDBACK_SQL, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, feedback.getTimestamp());
                ps.setInt(2, feedback.getQuestionId());
                ps.setInt(3, feedback.getUserId());
                ps.setInt(4, feedback.getConversationId());
                ps.setString(5, feedback.getPhoneNumber());
                ps.setString(6, feedback.getContent());
                return ps;
            }
        }, holder);
        return rst == 0;
    }

    public Conversation queryConversation(String conversationID) {
        // Conversation(int id, int scheduleId, int status, int userId, String convoID)
        final List<Conversation> rst = jdbcTemplate.query(
                "SELECT * FROM conversation WHERE convoID = ? ", new Object[] { conversationID },
                (rs, rowNum) -> new Conversation(rs.getInt("id")
                        , rs.getInt("scheduleId")
                        , rs.getInt("status")
                        , rs.getInt("userId")
                        , rs.getString("convoID")
                        , rs.getLong("createTime")
                        , rs.getLong("lastUpdateTime"))
        );
        Collections.sort(rst, new Comparator<Conversation>() {
            @Override public int compare(final Conversation o1, final Conversation o2) {
                return (int) (o2.getCreateTime() - o1.getCreateTime());
            }
        });

        if (rst != null && !rst.isEmpty()) {
            System.out.println("conversation returned " + rst.get(0));
            return rst.get(0);
        }

        return null;
    }

    public boolean updateConversaton(Conversation conversation) {
        System.out.println("update conversation " + conversation.toString());
        int rst = jdbcTemplate.update(
                "update conversation set status=? where id = ?",
                conversation.getStatus(), conversation.getId());
        rst = jdbcTemplate.update(
                "update conversation set lastUpdateTime=? where id = ?",
                System.currentTimeMillis(), conversation.getId());
        return rst == 0;
    }
}
