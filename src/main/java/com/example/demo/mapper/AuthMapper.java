package com.example.demo.mapper;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Result;

import com.example.demo.model.RefreshToken;
import com.example.demo.model.Roles;
import com.example.demo.model.User;
import com.google.common.base.Optional;

//import autovalue.shaded.com.google..common.base..Optional;
//
//import autovalue.shaded.com.google..common.base..Optional;

@Mapper
public interface AuthMapper {
    
	@Select("select top 1 input_1, input_2 from settings_data where key_identifier = 'login_trials' and status = 1 and availability = 1")
 	public Map<String, Integer> getLoginTrialsSettings();
	
	@Select("select top 1 input_1, input_2 from settings_data where key_identifier = 'login_trials' and status = 1 and availability = 1")
	public int getLoginTrials(@Param("email") String email);
	
	@Select("SELECT date FROM ("
			+ "  SELECT"
			+ "    ROW_NUMBER() OVER (ORDER BY id ASC) AS rownumber,"
			+ "    id, date "
			+ "  FROM login_trials where status = 1 and availability = 1 and email = #{email}"
			+ ") AS helper "
			+ "WHERE rownumber = #{input_1}")
	public String getDateOfTheFifthLoginTrial(@Param("input_1") int login_trial, @Param("email") String email);
	
	@Select("select input_1 from settings_data where key_identifier = 'login_fail_time_to_punish' and status = 1 and availability = 1")
 	public int loginGetTimeToPunish();
	
	@Select("IF EXISTS (SELECT * FROM refresh_token WHERE email = #{email}) " + "    SELECT 'True' "
			+ "ELSE " + " SELECT 'False'")
	public Boolean check_refreshTokenExistByEmail(@Param("email") String email);
    
	@Select("select status from functionalities where name = #{functionality}")
 	public String getStatusOfFunctionalityByFunctionName(String functionality);
	
	@Select("select r.* from roles r join user_role ur on r.id = ur.role_id where ur.user_id = #{userId} and r.status = 1 and r.availability = 1 and ur.status = 1 and ur.availability = 1")
	public Collection<Roles> getRolesByUserId(Long userId);
	
	@Select("select * from users where email = #{email} and status = 1 and availability = 1")
	@Results(value = { @Result(property = "id", column = "id"),
			@Result(property = "roles", javaType = List.class, column = "id", many = @Many(select = "com.example.demo.mapper.AuthMapper.getRolesByUserId")),
//			@Result(property = "rights", javaType = List.class, column = "id", many = @Many(select = "com.example.demo.mapper.MapperAuth.getUserRights")),
//			@Result(property = "subject", javaType = List.class, column = "id", many = @Many(select = "com.example.demo.mapper.MapperAuth.getSubjectsByUserId")) 
	})
	
	public User findByUserName2(String email);
	
	@Select("insert into refresh_token (email) OUTPUT Inserted.id values(#{email})")
	public Long addRefreshToken(RefreshToken refreshToken);
 
	
	@Select("select r.name from roles r, user_role ur where ur.user_id = #{user_id} and ur.role_id = r.id and"
			+ " ur.status = 1 and ur.availability = 1 and r.status = 1 and r.availability = 1;")
	public String[] getRoleByUserId(Long user_id);
	
	@Select("select id from users where email = #{email} and status = 1 and availability = 1")
	public Long getUserIdByEmail(@Param("email") String email);
	
	@Select("select status from roles where name = #{role}")
	public String getStatusOfRole(@Param("role") String role);
	
	@Select("insert into log (activity, ip, browser_type, browser_version, date, status, availability) OUTPUT Inserted.id "
			+ "values (#{activity}, #{ip}, #{browser_type}, #{browser_version}, #{date}, #{status}, #{availability})")
	public Long registerLog(@Param("ip") String ip, @Param("browser_type") String browser_type,
			@Param("browser_version") String browser_version, @Param("activity") String activity,
			@Param("date") String date, @Param("status") String status, @Param("availability") String availability);


	@Select("select rf.status from roles r, role_functionality rf, functionalities f where "
			+ "r.name = #{type} and r.id = rf.role_id and f.name = #{functionality} and f.id = rf.functionality_id")
	public String getStatusFromRoleFunctionalityByRoleAndFunctionality(@Param("type") String type,
			@Param("functionality") String functionality);
	
	@Insert("insert into user_log (user_id, log_id, status, availability) values (#{user_id}, #{log_id}, #{status}, #{availability})")
	public void registerUserLog(@Param("user_id") Long user_id, @Param("log_id") Long log_id,
			@Param("status") String status, @Param("availability") String availability);

	@Update("update login_trials set status=0 where email = #{email} and status = 1 and availability = 1")
	public void clearLoginTrials(@Param("email") String email);
	
	@Select("select top 1 '1' from users where email = #{email} and availability = 1")
	public Optional<String> check_UserExistByEmail_nostatus(String email);

	@Select("select top 1 '1' from users where email = #{email} and status = 1 and availability = 1")
	public Optional<String> check_UserExistByEmail(@Param("email") String email); 
	
	@Insert("insert into login_trials (date, email, ip, status, availability) values(#{date}, #{email}, #{ip}, #{status}, #{availability})")
	public void addLoginTrial(@Param("date") String date,@Param("email") String email,@Param("ip") String user_ip,
			@Param("status") String status,@Param("availability") String availability);

	@Select("select status from users where availability = 1 and email = #{email}")
	public String getUserStatusByEmail(@Param("email") String email);
	
	@Update("update users set status=0 where email = #{email} and status = 1 and availability = 1")
	public void disableUserLoginTrialExceeded(@Param("email") String email);
	
	@Select("select * from users where email = #{email} and status = 1 and availability = 1")
	@Results(value = { @Result(property = "id", column = "id"),
			@Result(property = "roles", javaType = List.class, column = "id", many = @Many(select = "com.example.demo.mapper.AuthMapper.getRolesByUserId")),
//			@Result(property = "rights", javaType = List.class, column = "id", many = @Many(select = "com.example.demo.mapper.MapperAuth.getUserRights")),
//			@Result(property = "subject", javaType = List.class, column = "id", many = @Many(select = "com.example.demo.mapper.MapperAuth.getSubjectsByUserId")) 
	})
	public User findByUserName(String email);
	
	@Delete("delete from refresh_token where email=#{email}")
	public void logdoutfromOtherDevice(String email);
	
	@Update("update users set firsttime = 1 where email = #{email}")
	public void update_firsttime_status(@Param("email") String email);

	@Update("update users set password = #{password}, firsttime = '1' where email = #{email}")
	public void reset_password_on_forget_password(@Param("password") String password, @Param("email") String email);
     
	@Select("select top 1 1 from refresh_token where id = #{refreshToken_id}")
	public Long tokenExists(Long refreshToken_id);
	
	@Delete("delete from refresh_token where id = #{refreshToken_id}")
	public void deleteRefreshToken(Long refreshToken_id);
	
	@Select("select * from users where id = #{id} and status = 1 and availability = 1")
	@Results(value = { @Result(property = "id", column = "id"),
			@Result(property = "roles", javaType = List.class, column = "id", many = @Many(select = "com.example.demo.mapper.AuthMapper.getRolesByUserId")),
//			@Result(property = "rights", javaType = List.class, column = "id", many = @Many(select = "com.example.demo.mapper.MapperAuth.getUserRights")),
//			@Result(property = "subject", javaType = List.class, column = "id", many = @Many(select = "com.example.demo.mapper.MapperAuth.getSubjectsByUserId")) 
	})
	public User findByUserName3(Long id);
	
	
	

}
