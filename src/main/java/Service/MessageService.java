package Service;

import DAO.MessageDAO;
import Model.Message;

import java.util.List;

public class MessageService {
    public MessageDAO messageDAO;

    /*
     * default constructor
     * */
    public MessageService(){
        messageDAO = new MessageDAO();
    }
    /*
     * @overloading
     * Constructor for a MessageService when a messageDAO is provided.
     * */
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    //3R
    public Message createMessage(Message message){
        String messageText = message.getMessage_text();
        if(messageText.isEmpty() || messageText.length() > 254 ||
                !messageDAO.checkExistingAccountID(message.getPosted_by())){
            return null;
        }
        return messageDAO.createMessage(message);
    }

    //4R
    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }
    //5R
    public Message getMessageByMessageID(int message_id){
        return messageDAO.getMessageByMessageID(message_id);
    }

    //6R
    public Message deleteMessageByMessageID(int message_id){
        Message message = getMessageByMessageID(message_id);
        if (message != null){
            return messageDAO.deleteMessageByMessageID(message);
        }
        return null;
    }

    //7R
    public Message updateMessageByMessageID(int message_id, String message_text){
        if (message_text.length() > 254 || message_text.isEmpty()){
            return null;
        }
        int updatedMessage = messageDAO.updateMessageByMessageID(message_id, message_text);
        if (updatedMessage > 0){
            return messageDAO.getMessageByMessageID(message_id);
        }
        return null;
    }

    //8R
    public List<Message> getAllMessagesByAccountID(int account_id){
        return messageDAO.getAllMessagesByAccountID(account_id);
    }
}
