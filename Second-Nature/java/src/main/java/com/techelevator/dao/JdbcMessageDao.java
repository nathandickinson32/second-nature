package com.techelevator.dao;

import com.techelevator.model.Messages.CreateMessageDto;
import com.techelevator.model.Messages.Message;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMessageDao implements MessageDao{
    private JdbcTemplate template;
    public JdbcMessageDao(DataSource ds){template = new JdbcTemplate(ds);}


    @Override
    public Message createMessage(CreateMessageDto createMessageDto, int userId) {
        String sql = "INSERT INTO messages(room_id, sender_id, message, created_at, is_archived) VALUES (?, ?, ?, ?, ?) RETURNING message_id;";
        int messageId = -1;
        try {
            messageId = template.queryForObject(
                    sql,
                    int.class,
                    createMessageDto.getRoomId(),
                    userId,
                    createMessageDto.getMessage(),
                    LocalDateTime.now(),
                    false
            );
        }catch (CannotGetJdbcConnectionException e){
            throw new CannotGetJdbcConnectionException("[JDBC Message DAO] Unable to connect to the database.");
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("[JDBC Message DAO] Unable to create a new Message.");
        }
        return getMessageById(messageId);
    }

    //READ

    public List<Message> getMessagesByRoomId(int roomId){
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE room_id = ?;";
        try {
            SqlRowSet results = template.queryForRowSet(sql,roomId);
            while(results.next()){
                Message message= mapRowToMessage(results);
                messages.add(message);
            }

        }catch (CannotGetJdbcConnectionException e){
            throw new CannotGetJdbcConnectionException("[JDBC Chat Room DAO] Unable to connect to the database.");
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("[JDBC Chat Room DAO] Unable to get members by Room Id.");
        }
        return messages;
    }

    public  Message getMessageById(int messageId){
        Message message = null;
        String sql = "SELECT * FROM messages WHERE message_id = ?;";

        try{
            SqlRowSet results = template.queryForRowSet(sql,messageId);
            if(results.next()){
                message = mapRowToMessage(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new CannotGetJdbcConnectionException("[JDBC Message DAO] Unable to connect to the database.");
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("[JDBC Message DAO] Unable to retrieve message by id: " + messageId);
        }
        return message;
    }


    //MAP
    public Message mapRowToMessage(SqlRowSet results){
        Message message = new Message();
        message.setMessageId(results.getInt("message_id"));
        message.setRoomId(results.getInt("room_id"));
        message.setSenderId(results.getInt("sender_id"));
        message.setMessage(results.getString("message"));
        message.setCreatedAt(results.getTimestamp("created_at").toLocalDateTime());
        message.setArchived(results.getBoolean("is_archived"));
        return message;
    }

}