package Controller;


import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

/*
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;
    ObjectMapper mapper;

    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
        this.mapper = new ObjectMapper();
    }
    /*
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::registerAccountHandler); //1R
        app.post("/login", this::loginAccountHandler);//2R
        app.post("/messages", this::createMessageHandler);//3R
        app.get("/messages", this::getAllMessagesHandler);//4R
        app.get("/messages/{message_id}", this::getMessageByMessageIDHandler);//5R
        app.delete("/messages/{message_id}", this::deleteMessageByMessageIDHandler);//6R
        app.patch("/messages/{message_id}", this::updateMessageByMessageIDHandler);//7R
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByAccountIDHandler);//8R
        return app;
    }

    /*
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    /*
     * 1R: Handler to register a new account
     */
    private void registerAccountHandler(Context ctx) throws JsonProcessingException {
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.register(account);
        if(addedAccount != null){
            ctx.json(mapper.writeValueAsString(addedAccount));
        } else {
            ctx.status(400);//Client Error
        }
    }
    /*
     * 2R: Handler to login existing account
     */
    private void loginAccountHandler(Context ctx) throws JsonProcessingException{
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account loggedinAccount = accountService.login(account);
        if(loggedinAccount != null){
            ctx.json(mapper.writeValueAsString(loggedinAccount));
        } else {
            ctx.status(401); //Unauthorized
        }
    }
    /*
     * 3R: Handler to create a new message
     */
    private void createMessageHandler(Context ctx) throws JsonProcessingException{
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message createdMessage = messageService.createMessage(message);
        if(createdMessage != null){
            ctx.json(mapper.writeValueAsString(createdMessage));
        } else {
            ctx.status(400);
        }
    }
    /*
     * 4R: Handler to retrieve all messages
     */
    private void getAllMessagesHandler(Context ctx) throws JsonProcessingException{
        List<Message> allMessages = messageService.getAllMessages();
        ctx.json(allMessages);
    }
    /*
     * 5R: Handler to retrieve a message using a message ID
     */
    private void getMessageByMessageIDHandler(Context ctx) throws  JsonProcessingException{
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message foundMessage = messageService.getMessageByMessageID(message_id);
        if (foundMessage != null) {
            ctx.json(foundMessage);
        } else {
            ctx.status(200);
        }
    }
    /*
     * 6R: Handler to delete a message using a message ID
     */
    private void deleteMessageByMessageIDHandler(Context ctx) throws  JsonProcessingException{
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message deletedMessage = messageService.deleteMessageByMessageID(message_id);
        if (deletedMessage != null) {
            ctx.json(deletedMessage);
        } else {
            ctx.status(200);
        }
    }

    /*
     * 7R: Handler to update a message using a message ID
     */
    private void updateMessageByMessageIDHandler(Context ctx) throws  JsonProcessingException{
        JsonNode jsonNode = mapper.readTree(ctx.body());
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        String message_text = jsonNode.get("message_text").asText();
        Message updatedMessage = messageService.updateMessageByMessageID(message_id, message_text);
        if (updatedMessage != null) {
            ctx.json(updatedMessage);
        } else {
            ctx.status(400);
        }
    }
    /*
     * 8R: Handler to retrieve all messages using an account ID
     */
    private void getAllMessagesByAccountIDHandler(Context ctx) throws  JsonProcessingException{
        int account_id = Integer.parseInt(ctx.pathParam("account_id"));
        List<Message> messages = messageService.getAllMessagesByAccountID(account_id);
        if (messages != null) {
            ctx.json(messages);
        } else {
            ctx.status(200);
        }
    }
}