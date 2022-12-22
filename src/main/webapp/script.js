function fn_submit(){
	var fn = document.frm;
	
	if (fn.v_jumin.value == "") {
		alert("주민번호가 입력되지 않았습니다.");
		fn.v_jumin.focus();
		return false;
	}
	
	if (fn.v_name.value == "") {
		alert("성명이 입력되지 않았습니다.");
		fn.v_name.focus();
		return false;
	}
	
	if (fn.m_no.value == "") {
		alert("투표번호가 입력되지 않았습니다.");
		fn.m_no.focus();
		return false;
	}
	
	if (fn.v_time.value == "") {
		alert("투표시간이 입력되지 않았습니다.");
		fn.v_time.focus();
		return false;
	}
	
	if (fn.v_area.value == "") {
		alert("투표장소가 입력되지 않았습니다.");
		fn.v_area.focus();
		return false;
	}
	
	if (fn.v_confirm.value == "") {
		alert("유권자 확인을 체크하지 않았습니다.");
		return false;
	}
	fn.submit();
}

