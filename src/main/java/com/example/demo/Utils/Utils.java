package com.example.demo.Utils;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.mapper.AuthMapper;
import com.google.api.client.util.Value;

@Component
public class Utils {

//	@Autowired
//	private JwtHelper jwtHelper;

	@Autowired
	private AuthMapper authMapper;

	
//	@Autowired
//	private MapperAccount accountMapper;
//
//	@Autowired
//	private AdminMapper adminMapper;
//
	@Value("${jwt.access.token.cookie.name}")
	private String cookieName;

	@Value("${jwt.refresh.token.cookie.name}")
	private String refreshTokenName;
//
//	@Autowired
//	private MapperCurrency currencyMapper;
//
//	public boolean check_AccountCodeExist(String acc) {
//		System.out.println("get code result from util service of code " + accountMapper.check_AccountCodeExist(acc));
//
//		return accountMapper.check_AccountCodeExist(acc).isPresent() ? false : true;
//	}
//
//	public boolean check_AcountCurrency(String acc, String name) {
//		System.out.println(
//				"get currency result from util service " + accountMapper.check_AccountCurrencyExist(acc, name));
//		return accountMapper.check_AccountCurrencyExist(acc, name).isPresent() ? false : true;
//
//	}
//
//	public boolean check_AcountNameNotExist(String acc) {
//		return accountMapper.check_AccountNameExist(acc).isPresent() ? false : true;
//	}
//
//	public boolean check_AcountNameExist(String acc) {
//		return accountMapper.check_AccountNameExist(acc).isPresent() ? true : false;
//	}
//
//	public boolean check_CurrencyCodeExist(String curr) {
//		return currencyMapper.check_CurrencyCodeExist(curr).isPresent() ? false : true;
//	}
//
//	public boolean check_Role_Exist(String curr) {
//		return currencyMapper.check_roleExist(curr).isPresent() ? false : true;
//	}
//
//	public boolean check_IfFileIsUploaded(String transaction_date, Long user_account_id, String recon_left_right) {
//		return accountMapper.getWorkspaceIdFromReconciliationType(transaction_date, user_account_id, recon_left_right)
//				.isPresent() ? true : false;
//	}
//
//	public String get_user_account(HttpServletRequest request) {
//		Optional<String> ret = accountMapper.getUserAccountAndCurrency(get_user_id(request));
//		System.out.println("user has account: " + ret);
//		if (ret.isPresent())
//			return ret.get();
//		return null;
//	}
//
////	public String get_user_workspace(HttpServletRequest request) {
////		Optional<String> ret = authMapper.userHasWorkspace(get_user_id(request));
////		System.out.println("user has workspace: " + ret);
////		if (ret.isPresent())// getUserWorkspace
////			return authMapper.getUserWorkspace(get_user_id(request));
////		return null;
////	}
//
//	public boolean removeUserFromWorkspace(HttpServletRequest request) {
//		Optional<String> ret = authMapper.userHasWorkspace(get_user_id(request));
//		System.out.println("user has workspace: " + ret);
//		if (ret.isPresent() ? true : false)
//			authMapper.removeUserFromWorkspace(get_user_id(request));
//		return true;
//	}
//
//	public boolean isWorkspaceFree(String reconciliation_type) {
//		Long id = authMapper.getWorkspaceIdFromReconciliationType(reconciliation_type);
//		Optional<String> ret = authMapper.check_IfWorkspaceIsFree(id);
//		System.out.println("output ret: " + ret + " : " + reconciliation_type + " : " + id);
//		return ret.isPresent() ? false : true;
//	}
//
	public boolean doesUserExistByEmail(String email) {
		return authMapper.check_UserExistByEmail(email).isPresent() ? true : false;
	}
//
//	public boolean doesUserExistByEmail_nostatus(String email) {
//		return authMapper.check_UserExistByEmail_nostatus(email).isPresent() ? true : false;
//	}

	public boolean isUserDisabled(String email) {
		System.out.println("isUserDisabled: " + "0".equals(authMapper.getUserStatusByEmail(email)));
		if (doesUserExistByEmail_nostatus(email))
			return "0".equals(authMapper.getUserStatusByEmail(email));
		else
			return false;
	}
	
	public boolean doesUserExistByEmail_nostatus(String email) {
		return authMapper.check_UserExistByEmail_nostatus(email).isPresent() ? true : false;
	}
//
//	public boolean isUserLoggedInAndisEmailBeingUpdated(Long user_id, String email) {
//		Optional<String> ret = adminMapper.check_userLoggedIn(adminMapper.getEmailFromId(user_id));
//		System.out.println("ret: " + ret);
//		return (ret.isPresent() && !email.equalsIgnoreCase(adminMapper.getEmailFromId(user_id))) ? true : false;
//	}

	public boolean isPermitted_outside(String functionality) {
		System.out.println("the functionality: " + authMapper.getStatusOfFunctionalityByFunctionName(functionality));
		if ("1".equalsIgnoreCase(authMapper.getStatusOfFunctionalityByFunctionName(functionality)))
			return true;
		return false;
	}
//
//	public Long get_user_id(HttpServletRequest request) {
//		return authMapper.getUserIdByEmail(jwtHelper.getEmailFromAccessToken(getToken(request)));
//	}
//
//	public boolean is_Loggedin(HttpServletRequest request) {
//		return authMapper.check_refreshTokenExist(jwtHelper.getClaim(getRefreshToken(request), "tokenId")) != null
//				? true
//				: false;
//	}
//
	public boolean isPermitted_outside_second(HttpServletRequest request, String type, String functionality,
			String email) {
		System.out.println("role type: " + type);
		System.out.println("functionality: " + functionality);
		String[] roles = authMapper.getRoleByUserId(authMapper.getUserIdByEmail(email));
		for (int i = 0; i < roles.length; i++) {
			if (type.equalsIgnoreCase(roles[i]) && "1".equalsIgnoreCase(authMapper.getStatusOfRole(type)))
				if ("1".equalsIgnoreCase(authMapper.getStatusOfFunctionalityByFunctionName(functionality)))
					if ("1".equalsIgnoreCase(
							authMapper.getStatusFromRoleFunctionalityByRoleAndFunctionality(type, functionality)))
						return true;
		}
		return false;

	}
	
 
//	public boolean isPermitted(HttpServletRequest request, String type, String functionality) {
//		String accessToken = getToken(request);
//		Long user_id = authMapper.getUserIdByEmail(jwtHelper.getEmailFromAccessToken(accessToken));
//		String[] roles = authMapper.getRoleByUserId(user_id);
//		System.out.println("The type is: " + type);
//		System.out.println("The type is: " + functionality);
//		if (accessToken != null) {
//			for (int i = 0; i < roles.length; i++) {
//				if (type.equalsIgnoreCase(roles[i]) && "1".equalsIgnoreCase(authMapper.getStatusOfRole(type)))
//					if ("1".equalsIgnoreCase(authMapper.getStatusOfFunctionalityByFunctionName(functionality)))
//						if ("1".equalsIgnoreCase(
//								authMapper.getStatusFromRoleFunctionalityByRoleAndFunctionality(type, functionality)))
//							if ("1".equalsIgnoreCase(authMapper.getUserStatus(user_id))
//									&& "1".equalsIgnoreCase(authMapper.getUserAvailability(user_id)))
//								if (is_Loggedin(request))
//									return true;
//								else
//									System.out.println("66");
//							else
//								System.out.println("55");
//						else
//							System.out.println("44");
//					else
//						System.out.println("33");
//				else
//					System.out.println("22");
//			}
//		} else
//			System.out.println("11");
//		return false;
//	}
//
//	public String getUserName(HttpServletRequest request) {
//		String accessToken = getToken(request);
//		return authMapper.getUserName(authMapper.getUserIdByEmail(jwtHelper.getEmailFromAccessToken(accessToken)));
//	}
//
//	public boolean userHasRole(HttpServletRequest request, String role) {
//		String accessToken = getToken(request);
//		Long user_id = authMapper.getUserIdByEmail(jwtHelper.getEmailFromAccessToken(accessToken));
//		String[] roles = authMapper.getRoleByUserId(user_id);
//		if (accessToken != null) {
//			for (int i = 0; i < roles.length; i++) {
//				if (roles[i].equalsIgnoreCase(role))
//					return true;
//			}
//		}
//		return false;
//	}
//
//	public boolean isPermitted_no_role(HttpServletRequest request, String functionality) {
//		String accessToken = getToken(request);
//		Long user_id = authMapper.getUserIdByEmail(jwtHelper.getEmailFromAccessToken(accessToken));
//		String[] roles = authMapper.getRoleByUserId(user_id);
//		if (accessToken != null) {
//			for (int i = 0; i < roles.length; i++) {
//				if ("1".equalsIgnoreCase(authMapper.getStatusOfRole(roles[i])))
//					if ("1".equalsIgnoreCase(authMapper.getStatusOfFunctionalityByFunctionName(functionality)))
//						if ("1".equalsIgnoreCase(authMapper
//								.getStatusFromRoleFunctionalityByRoleAndFunctionality(roles[i], functionality)))
//							if ("1".equalsIgnoreCase(authMapper.getUserStatus(user_id))
//									&& "1".equalsIgnoreCase(authMapper.getUserAvailability(user_id)))
//								if (is_Loggedin(request))
//									return true;
//			}
//		}
//		return false;
//	}
//
//	public boolean isPermittedNoRole(HttpServletRequest request, String functionality) {
//		String accessToken = getToken(request);
//		if (accessToken != null)
//			if ("1".equalsIgnoreCase(authMapper.getStatusOfFunctionalityByFunctionName(functionality)))
//				return true;
//		return false;
//	}
//
//	public Long getUserId(HttpServletRequest request) {
//		return authMapper.getUserIdByEmail(jwtHelper.getEmailFromAccessToken(getToken(request)));
//	}
//
//	public String getUserEmail(HttpServletRequest request) {
//		return jwtHelper.getEmailFromAccessToken(getToken(request));
//	}
//
	public void registerLog(Long id, String user_ip, String browser_type, String browser_version, String activity,
			String status, String availability) {
		authMapper.registerUserLog(id,
				authMapper.registerLog(user_ip, browser_type, browser_version, activity,
						DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a").format(LocalDateTime.now()), status,
						availability),
				"1", "1");
	}
	
//
//	public void registerActivity(HttpServletRequest request, String activity, String description) {
//		String accessToken = getToken(request);
//		if (accessToken != null)
//			authMapper.registerUserActivity(authMapper.getUserIdByEmail(jwtHelper.getEmailFromAccessToken(accessToken)),
//					authMapper.registerActivity(activity, description,
//							DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a").format(LocalDateTime.now()), "1",
//							"1"),
//					"1", "1");
//	}
//
////	public void registerActivityMM(Long user_id, String activity, String description) {
////		authMapper.registerUserActivity(user_id,
////				authMapper.registerActivity(activity, description,
////						DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a").format(LocalDateTime.now()), "1",
////						"1"), "1", "1");
////	}
//
//	private String getToken(HttpServletRequest request) {
//		Cookie cookie = WebUtils.getCookie(request, cookieName);
//		return cookie != null ? cookie.getValue() : null;
//	}
//
//	private String getRefreshToken(HttpServletRequest request) {
//		Cookie cookie = WebUtils.getCookie(request, refreshTokenName);
//		return cookie != null ? cookie.getValue() : null;
//	}

	
}
