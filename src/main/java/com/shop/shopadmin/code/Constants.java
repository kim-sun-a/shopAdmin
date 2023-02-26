package com.shop.shopadmin.code;

import com.shop.shopadmin.entity.Member;
import org.springframework.security.core.context.SecurityContextHolder;

public class Constants {
    public static final String applicationName = "SHOPADMIN";
    public static final String roleprefix = "ROLE_";
    public static final String unitName = "shop";

    public static String principalUserName() {
        String principalUserName = applicationName;
        if(SecurityContextHolder.getContext() != null
                && SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof Member
        ) {
            Member user = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            principalUserName = user.getName() + "("+user.getId()+")";
        }
        return principalUserName;
    }

}
