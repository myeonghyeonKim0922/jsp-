
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Member;
import DTO.RankDTO;
import DTO.Vote;

public class MemberDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public static Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection
			("jdbc:oracle:thin:@//localhost:1521/xe","system","sys1234");
		return con;
	}
	public String select1(HttpServletRequest request, HttpServletResponse response) {
	ArrayList<Member> list = new ArrayList<Member>();
	
	try {
		conn = getConnection();
		
		String sql ="select m1.m_no, m1.m_name, m1.p_code, m1.p_school, SUBSTR(m1.m_jumin,1,6)||'-'||substr(m1.m_jumin,7) jumin, m1.m_city, p1.p_tel1, p1.p_tel2, SUBSTR(p1.p_tel3,4)||SUBSTR(p1.p_tel3,4)||SUBSTR(p1.p_tel3,4)||SUBSTR(p1.p_tel3,4),SUBSTR(p1.p_tel3,4) from TBL_MEMBER_202005 m1 ,tbl_party_202005 p1 where m1.p_code = p1.p_code";
		
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		
		while(rs.next()) {
			Member member = new Member();
			
			
			member.setM_no(rs.getString(1));
			member.setM_name(rs.getString(2));
			member.setM_jumin(rs.getString(5));
			member.setM_city(rs.getString(6));
			member.setP_tel1(rs.getString(7));
			member.setP_tel2(rs.getString(8));
			member.setP_tel3(rs.getString(9));
			
			if(rs.getString(3).equals("P1")) {
				member.setP_code("A정당");
			}else if(rs.getString(3).equals("P2")) {
				member.setP_code("B정당");
			}else if(rs.getString(3).equals("P3")) {
				member.setP_code("C정당");
			}else if(rs.getString(3).equals("P4")) {
				member.setP_code("D정당");
			}else if(rs.getString(3).equals("P5")) {
				member.setP_code("E정당");
			}
			
			if(rs.getString(4).equals("1")) {
				member.setP_school("고졸");
			}else if(rs.getString(4).equals("2")) {
				member.setP_school("학사");
			}else if(rs.getString(4).equals("3")) {
				member.setP_school("석사");
			}else if(rs.getString(4).equals("4")) {
				member.setP_school("박사");
			}
			
			list.add(member);
		}
		request.setAttribute("list", list);
		
		conn.close();
		ps.close();
		rs.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "lookup.jsp";
	}
	public int insert(HttpServletRequest request, HttpServletResponse response) {
		String v_jumin = request.getParameter("v_jumin");
		String v_name = request.getParameter("v_name");
		String m_no = request.getParameter("m_no");
		String v_time = request.getParameter("v_time");
		String v_area = request.getParameter("v_area");
		String v_confirm = request.getParameter("v_confirm");
		
		
		int result =  0;
		try {
			conn = getConnection();
			String sql = "insert into tbl_vote_202005 values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, v_jumin);
			ps.setString(2, v_name);
			ps.setString(3, m_no);
			ps.setString(4, v_time);
			ps.setString(5, v_area);
			ps.setString(6, v_confirm);
			
			
			result = ps.executeUpdate();

			System.out.println(result);
			
			conn.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;	
	}
	public String select2(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Vote> list = new ArrayList<Vote>();
		try {
			conn = getConnection();
			String sql = "select v_name, '19'||SUBSTR(v_jumin, 1, 2)||'년'||SUBSTR(v_jumin, 3, 2)||'월'||SUBSTR(v_jumin, 5, 2)||'일생' birth,120 - SUBSTR(v_jumin,1,2) age, SUBSTR(v_jumin,7,1), m_no, SUBSTR(v_time, 1,2)||':'||SUBSTR(v_time, 3,2), v_confirm  from tbl_vote_202005 where v_area='제1투표장'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Vote v = new Vote();
				v.setV_name(rs.getString(1));
				v.setV_birth(rs.getString(2));
				v.setV_age(rs.getString(3));
				v.setM_no(rs.getString(5));
				v.setV_time(rs.getString(6));
				v.setV_confirm(rs.getString(7));
				
				if(rs.getString(4).equals("1")) {
					v.setV_s("남");
				}else {
					v.setV_s("여");
				}
				
				if(rs.getString(7).equals("Y")) {
					v.setV_confirm("확인");
				}else {
					v.setV_confirm("미확인");					
				}
				
				list.add(v);
			}
			
			request.setAttribute("list", list);
			
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "voteins.jsp";
	}
	public String rank(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<RankDTO> list = new ArrayList<RankDTO>();
		
		try {
			conn = getConnection();
			
			String sql = "select m1.m_no, Count(v1.m_no) as count from tbl_vote_202005 v1, tbl_member_202005 m1 where m1.m_no = v1.m_no and v1.v_confirm = 'Y' GROUP BY m1.m_no order by count desc";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				RankDTO r = new RankDTO();
				
				r.setM_no(rs.getString(1));
				r.setV_vote(rs.getString(2));
				
				if(rs.getString(1).equals("1")) {
					r.setM_name("김후보");
				}else if(rs.getString(1).equals("2")) {
					r.setM_name("이후보");
				}else if(rs.getString(1).equals("3")) {
					r.setM_name("박후보");
				}else if(rs.getString(1).equals("4")) {
					r.setM_name("조후보");
				}else if(rs.getString(1).equals("5")) {
					r.setM_name("최후보");
				}
				
				list.add(r);
			}
			
			request.setAttribute("list", list);
			
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "rank.jsp";
	}
}