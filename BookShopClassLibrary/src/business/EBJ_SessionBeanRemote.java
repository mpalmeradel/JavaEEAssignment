/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package business;

import java.util.List;
import javax.ejb.Remote;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author mpalm
 */
@Remote
public interface EBJ_SessionBeanRemote {

    Object action(Object sessionObjects, String actionKey);
    
}
