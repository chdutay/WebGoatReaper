package org.owasp.webgoat.plugin;

import org.jcodings.util.Hash;
import org.owasp.webgoat.session.UserSessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by appsec on 7/18/17.
 */
public class AccountVerificationHelper {

    public static String PARAM_0 = "secQuestion0";
    public static String PARAM_1 = "secQuestion1";

    //simulating database storage of verification credentials
    private  static final Integer verifyUserId = new Integer(1223445);
    private static final Map<String,String> userSecQuestions = new HashMap<>();
    static {
        userSecQuestions.put(PARAM_0,"Dr. Watson");
        userSecQuestions.put(PARAM_1,"Baker Street");
    }

    private static final Map<Integer,Map> secQuestionStore = new HashMap<>();
    static {
        secQuestionStore.put(verifyUserId,userSecQuestions);
    }
    // end 'data store set up'

    // this is to aid feedback in the attack process and is not intended to be part of the 'vulnerable' code
    public boolean didUserLikelylCheat(HashMap<String,String> submittedAnswers) {
        boolean likely = false;

        if (submittedAnswers.size() == secQuestionStore.get(verifyUserId).size()) {
            likely = true;
        }

        if ((submittedAnswers.containsKey(PARAM_0) && submittedAnswers.get(PARAM_0).equals(secQuestionStore.get(verifyUserId).get(PARAM_0))) &&
                (submittedAnswers.containsKey(PARAM_1) && submittedAnswers.get(PARAM_1).equals(secQuestionStore.get(verifyUserId).get(PARAM_1))) ) {
            likely = true;
        } else {
            likely = false;
        }

        return likely;

    }
    //end of cheating check ... the method below is the one of real interest. Can you find the flaw?

    public boolean verifyAccount(Integer userId, HashMap<String,String> submittedQuestions ) {
        boolean check1 = false;
        boolean check2 = false;
        if(!CollectionUtils.isEmpty(submittedQuestions)){
            if (submittedQuestions.containsKey(PARAM_0) && submittedQuestions.get(PARAM_0).equals(secQuestionStore.get(verifyUserId).get(PARAM_0))) {
                check1=true;
            }
            if (submittedQuestions.containsKey(PARAM_1) && submittedQuestions.get(PARAM_1).equals(secQuestionStore.get(verifyUserId).get(PARAM_1))) {
                check2=true;
            }
        }
        return check1 && check2;
    }
}
