package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    /*
    * default constructor
    * */
    public AccountService(){
        accountDAO = new AccountDAO();
    }
    /*
     * @overloading
     * Constructor for a AccountService when an AccountDAO is provided.
     * */
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    //1R
    public Account register(Account account){
        String username = account.getUsername();
        if(username.isEmpty() ||
                account.getPassword().length() <= 4 ||
                accountDAO.checkExistingUsername(username)){
            return null;
        }
        return accountDAO.registerAccount(account);
    }

    //2R
    public Account login(Account account){
        return accountDAO.loginAccount(account);
    }
}
