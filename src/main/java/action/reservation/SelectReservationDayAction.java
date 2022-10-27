package action.reservation;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

public class SelectReservationDayAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//--------------------------------달력 출력을 위한 부분------------------------------------//
  		Calendar cal = Calendar.getInstance();

  		String yearR = request.getParameter("year");//아래에서 입력한 2021
  		String monthR = request.getParameter("month");//아래에서 입력한 10

  		//값을 파라미터로 전송받지 않았을 때, 기본값
  		int year = cal.get(Calendar.YEAR);
  		int month = cal.get(Calendar.MONTH);

  		if(yearR != null && monthR != null && !yearR.trim().equals("") && !monthR.trim().equals("")){
  			year = Integer.parseInt(yearR);
  			month = Integer.parseInt(monthR)-1;//★★중요:Calendar의 월 0~11이므로
  		}

  		//달력 시작 시점 지정, 세팅한 시점부터 값을 결정
  		cal.set(year, month, 1);//리턴 타입이 void이므로
  		//Calendar.DAY_OF_WEEK :그 달의 시작일의 요일을 알 수 있다.(1~7 사이의 정수 리턴:1일~7토)
  		int dayOfweek = cal.get(Calendar.DAY_OF_WEEK);//월의 시작일을 정수로 리턴(2022년 4월 1일 금요일이면 6을 리턴)
  		//달력 마지막 날 지정(예 : 30 또는 31 또는 2월달은 26/28 리턴함)
  		int lastday = cal.getActualMaximum(Calendar.DATE);//2022년 4월이라면 30을 리턴

  		//*----------------------------------------*/
  		//◀ 이전버튼
  		int before_year = year;
  		int before_month = month;//★★주의 : before_month값은 month(월 0~11)이므로 그대로 저장가능
  		if(before_month == 0){//◀ 이전버튼 클릭하여 월이 0(=1월)이 되면
  			before_year = before_year - 1;//년도를 1감소
  			before_month = 12;//12월로
  		}

  		//▶ 다음버튼
  		int after_year = year;
  		int after_month = month+2;//★★주의 : after_month값은 month(월 0~11)에 2를 더하여 월2~13으로 만들어 저장해야 함
  		if(after_month == 13){//▶ 다음버튼 클릭하여 월이 13이 되면
  			after_year = after_year + 1;//년도를 1증가
  			after_month = 1;//1월로
  		}
  		
  		//request.setAttribute("yearR", yearR);
  		//request.setAttribute("monthR", monthR);
  		request.setAttribute("year", year);
  		request.setAttribute("month", month);
  		request.setAttribute("dayOfweek", dayOfweek);
  		request.setAttribute("lastday", lastday);
  		request.setAttribute("before_year", before_year);
  		request.setAttribute("before_month", before_month);
  		request.setAttribute("after_year", after_year);
  		request.setAttribute("after_month", after_month);
  		
  		//--------------------------------달력 출력을 위한 부분------------------------------------//
		forward = new ActionForward("reservation/selectReservationDay.jsp", false);
  		
		return forward;
	}

}
