package cf.scenecho.library.util;

import cf.scenecho.library.account.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

public class SessionUtil {
    private static final Logger logger = LoggerFactory.getLogger(SessionUtil.class);
    public static final String ACCOUNT = "account";

    public static boolean hasNoAttribute(HttpSession session) {
        logger.debug("- validating session... session: {}", session);
        return session.getAttribute(ACCOUNT) == null;
    }

    public static Account getAccount(HttpSession session) {
        return (Account) session.getAttribute(ACCOUNT);
    }

    public static void setAccount(HttpSession session, Account validAccount) {
        session.setAttribute(ACCOUNT, validAccount);
    }

    public static void removeAccount(HttpSession session) {
        session.removeAttribute(ACCOUNT);
    }
}
