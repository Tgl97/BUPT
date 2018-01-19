package com.org.mvc.controller;

import com.org.help.*;
import com.org.model.operationLog.HostOperationLog;
import com.org.model.operationLog.SlaveOperationLog;
import com.org.model.stateLog.HostStateLog;
import com.org.model.stateLog.SlaveStateLog;
import com.org.model.statistic.OnOffStatistic;
import com.org.model.statistic.RequestStopStatistic;
import com.org.repository.*;
import com.org.run.Run;
import com.org.run.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lizhongwei on 2017/4/15.
 * ҳ�����������
 */
@Controller
@RequestMapping("/")
public class HelloController {
	// ���ڹ���
	DateMessage dateMessage;
	// ͳ������
	List<StatisticData> statisticDatas = new ArrayList<StatisticData>();
	// �û�����
	List<UserData> userDatas = new ArrayList<UserData>();
	// �������ݿ��ѯ������@Autowired��ʼ������ֻ����controller����ʹ��
	@Autowired
	OnOffStatisticRepository onOffStatisticRepository;
	@Autowired
	RequestStopStatisticRepository requestStopStatisticRepository;
	@Autowired
	SlaveOperationLogRepository slaveOperationLogRepository;
	@Autowired
	SlaveStateLogRepository slaveStateLogRepository;
	@Autowired
	HostOperationLogRepository hostOperationLogRepository;
	@Autowired
	HostStateLogRepository hostStateLogRepository;

	// ����״̬����
	boolean mode = true;
	int temp_min = 10;
	int temp_max = 30;
	int default_wind = 1;
	int target_temp = 26;

	/**
	 * @desc ��ȡ����
	 * @author Created by lizhongwei on 2017/4/15.
	 * @param x ������
	 * @param y ����
	 * @return x / y ��������
	 */
	private int ceil(int x, int y) {
		if (x % y != 0)
			return x / y + 1;

		return x / y;
	}

	/**
	 * @desc ���캯��
	 * @author Created by lizhongwei on 2017/4/15.
	 */
	public HelloController(){
		Run.run();
	}

	/**
	 * @desc ����������
	 * @author Created by genglintong on 2017/4/15.
	 * @param request ����
	 * @param response ����
	 * @param session �Ự
	 * @return ��֤�ɹ��򷵻���ҳ�棬ʧ����ͣ���ڵ���ҳ��
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		String uname = request.getParameter("userid");
		String upasswd = request.getParameter("userpassid");

		session.setAttribute("uname",uname);
		session.setAttribute("upasswd",upasswd);
		//System.out.println(uname + "  " + upasswd);

		if(Judge(uname,upasswd)) {
			//System.out.println("*&&&"+Judge(uname,upasswd));
			return "index";
		}
		else
			return "login";
	}

	/**
	 * @desc ������
	 * @author Created by genglintong on 2017/4/15.
	 * @param uname �û���
	 * @param upasswd ����
	 * @return �ɹ�Ϊtrue��ʧ��Ϊfalse
	 */
	public static boolean Judge(String uname,String upasswd){
		//EntityManager entityManager = entityManagerFactory.createEntityManager();
		//entityManager.getTransaction().begin();
		//System.out.println(uname + "  " +upasswd);

		if(uname.equals("root") && upasswd.equals("bupt")) return true;
		else return false;
	}

	/**
	 * @desc ��ʼҳ��������
	 * @author Created by lizhongwei on 2017/4/15.
	 * @param model ҳ��ӳ�����
	 * @return ����ҳ��
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) throws IOException {
		return "login";
	}

	/**
	 * @desc ���ҳ��������
	 * @author Created by lizhongwei on 2017/4/15.
	 * @param modelMap ҳ��ӳ�����
	 * @return ���ҳ��
	 */
	@RequestMapping(value = "/table_monitor", method = RequestMethod.GET)
	public String showMonitor(ModelMap modelMap) {
		return "/table_monitor";
	}

	/**
	 * @desc ˢ��ҳ��������
	 * @author Created by lizhongwei on 2017/4/15.
	 * @param modelMap ҳ��ӳ�����
	 * @return ˢ��ҳ��
	 */
	@RequestMapping(value = "/iframe_monitor", method = RequestMethod.GET)
	public String showUsers(ModelMap modelMap) {
		userDatas.clear();
		ArrayList<User> users = Run.getUsers();

		// ��User��������UserData����
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			nf.setMaximumFractionDigits(2);
			String fee = nf.format(user.getFree());
			userDatas.add(new UserData(user, Run._is_cool, fee));
		}

		// ��userDatasӳ�䵽ҳ���users������
		modelMap.addAttribute("users", userDatas);
		return "/iframe_monitor";
	}

	/**
	 * @desc �鿴�굥������
	 * @author Created by lizhongwei on 2017/4/15.
	 * @param userId ��ҳ�洫���id
	 * @param modelMap ҳ��ӳ�����
	 * @return ���ݴ����id���ض�Ӧ���굥ҳ��
	 */
	@RequestMapping(value = "/view_bill{userId}", method = RequestMethod.GET)
	public String showBill(@PathVariable("userId")Integer userId, ModelMap modelMap) {
		User user = Run.getUser_list().get(userId);
		modelMap.addAttribute("userId", userId);
		modelMap.addAttribute("beginTime", user.getOn_time());
		modelMap.addAttribute("endTime", user.getOff_time());
		modelMap.addAttribute("serverTime", user.getLow_wind_time() + user.getMedium_wind_time() + user.getHigh_wind_time());
		modelMap.addAttribute("lowTime", user.getLow_wind_time());
		modelMap.addAttribute("middleTime", user.getMedium_wind_time());
		modelMap.addAttribute("highTime", user.getHigh_wind_time());
		int servTime = user.getLow_wind_time() + user.getMedium_wind_time() + user.getHigh_wind_time();
		int freeTime = (int) ((user.getOff_time().getTime() - user.getOn_time().getTime()) / 1000 - servTime);
		modelMap.addAttribute("freeTime", freeTime);
		double energyConsume = user.getLow_wind_time()/60.0*0.75 + user.getMedium_wind_time()/60.0 + user.getHigh_wind_time()/60.0*1.25;
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		nf.setMaximumFractionDigits(2);
		String energyConsumeStr = nf.format(energyConsume);
		modelMap.addAttribute("energyConsume", energyConsume);
		modelMap.addAttribute("totalFee", nf.format(user.getFree()));

		List<OnOffStatistic> onOffList = new ArrayList<OnOffStatistic>();
		onOffList.addAll(onOffStatisticRepository.find(userId, user.getOn_time(), new Date()));
		ArrayList<RequestStopData> requestStopDatas = new ArrayList<>();
		for (OnOffStatistic e : onOffList) {
			ArrayList<RequestStopStatistic> tmpList = (ArrayList<RequestStopStatistic>) requestStopStatisticRepository.find(e.getKeyId(), e.getPowerOnTime(), e.getPowerOffTime());
			for (int k = 0; k < tmpList.size(); ++k) {
				Date requestTime = tmpList.get(k).getRequestTime();
				Date stopTime = tmpList.get(k).getStopTime();
				RequestStopData rsd = new RequestStopData(requestTime, stopTime, tmpList.get(k).getRequestLevelWind(),(stopTime.getTime() - requestTime.getTime())/1000);
				requestStopDatas.add(rsd);
			}
		}

		if (requestStopDatas.size() != 0)
			modelMap.addAttribute("rqList", requestStopDatas);

		return "view_bill";
	}

	/**
	 * @desc ����ͳ��������
	 * @author Created by lizhongwei on 2017/4/15.
	 * @param userId ��ҳ�洫���id
	 * @param modelMap ҳ��ӳ�����
	 * @return ����ͳ����������ҳ��
	 */
	@RequestMapping(value = "/table_statistics_condition{userId}", method = RequestMethod.GET)
	public String showStatisticsCondition(@PathVariable("userId")Integer userId,ModelMap modelMap) {
		modelMap.addAttribute("userId", userId);
		return "table_statistics_condition";
	}

	/**
	 * @desc ����ͳ��������
	 * @author Created by lizhongwei on 2017/4/15.
	 * @param request ����
	 * @param response ����
	 * @param userId ��ҳ�洫���id
	 * @param modelMap ҳ��ӳ�����
	 * @return ����ͳ�ƽ��ҳ��
	 */
	@RequestMapping(value = "/showStatistics{userId}", method = RequestMethod.GET)
	public String showStatistics(HttpServletRequest request, HttpServletResponse response, @PathVariable("userId")Integer userId, ModelMap modelMap) {
		statisticDatas.clear();
		// ��ҳ���ȡ��������
		dateMessage = new DateMessage(request.getParameter("beginDate"),
				request.getParameter("endDate"),
				request.getParameter("size"),
				request.getParameter("timeInterval"));

		modelMap.addAttribute("beginDate", dateMessage.beginDate);
		modelMap.addAttribute("endDate", dateMessage.endDate);
		modelMap.addAttribute("size", dateMessage.size);
		modelMap.addAttribute("timeInterval", dateMessage.timeInterval);

		int cnt = 0;
		try {
			cnt = dateMessage.getDays() + 1;
		}
		catch (ParseException e) {
			e.printStackTrace();
		}

		// ��ʼ������
		int windTime[] = new int[3];
		double energyConsume = 0;
		double fee = 0;

		List<RequestStopData> requestStopDatas = new ArrayList<>();
		List<OnOffStatistic> onOffList = new ArrayList<OnOffStatistic>();
		StatisticData data;

		int i = 1;
		boolean dataFlag = false;

		// ͳ����Ҫ������
		for (int j = 0; i <= cnt; i++, j++) {

			onOffList.clear();
			try {
				Date be[] = dateMessage.addDay(j);
				onOffList.addAll(onOffStatisticRepository.find(userId, be[0], be[1]));
				for (OnOffStatistic e : onOffList) {
					ArrayList<RequestStopStatistic> tmpList = (ArrayList<RequestStopStatistic>) requestStopStatisticRepository.find(e.getKeyId(), e.getPowerOnTime(), e.getPowerOffTime());
					for (int k = 0; k < tmpList.size(); ++k) {
						Date requestTime = tmpList.get(k).getRequestTime();
						Date stopTime = tmpList.get(k).getStopTime();
						RequestStopData rsd = new RequestStopData(requestTime, stopTime, tmpList.get(k).getRequestLevelWind(),(stopTime.getTime() - requestTime.getTime())/1000);
						requestStopDatas.add(rsd);
					}
				}

				for (OnOffStatistic e : onOffList) {
					windTime[0] += e.getLowLevelWindTime();
					windTime[1] += e.getMiddleLevelWindTime();
					windTime[2] += e.getHighLevelWindTime();
					fee += e.getTotalFee();
				}
				energyConsume += windTime[0]*0.75 + windTime[1] + windTime[2]*1.25;
				String beginTime = dateMessage.getBeginTime();

				if (dateMessage.size.equals("week") && (i % 7 == 0 && i / 7 >= 1)) {
					dataFlag = true;
				}
				else if (dateMessage.size.equals("month") && (i % 30 == 0 && i / 30 >= 1)) {
					dataFlag = true;
				}
				else if (dateMessage.size.equals("day")){
					dataFlag = true;
				}

				if (dataFlag) {
					data = new StatisticData(beginTime, onOffList.size(), onOffList.size(),
							windTime[0], windTime[1], windTime[2], requestStopDatas.size(), energyConsume, fee, requestStopDatas);
					windTime[0] = 0;
					windTime[1] = 0;
					windTime[2] = 0;
					energyConsume = 0;
					fee = 0;
					statisticDatas.add(data);
					requestStopDatas.clear();
					dataFlag = false;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		String beginTime = dateMessage.getBeginTime();
		i--;
		if (dateMessage.size.equals("week") && i % 7 != 0) {
			dataFlag = true;
		}
		else if (dateMessage.size.equals("month") && i % 30 != 0) {
			dataFlag = true;
		}

		if (dataFlag) {
			data = new StatisticData(beginTime, onOffList.size(), onOffList.size(),
					windTime[0], windTime[1], windTime[2], requestStopDatas.size(), energyConsume, fee, requestStopDatas);
			windTime[0] = 0;
			windTime[1] = 0;
			windTime[2] = 0;
			energyConsume = 0;
			fee = 0;
			statisticDatas.add(data);
			requestStopDatas.clear();
			dataFlag = false;
		}
		modelMap.addAttribute("list", statisticDatas);

		return "/table_statistics_result";
	}

	/**
	 * @desc ���ȴ���ҳ��������
	 * @author Created by lizhongwei on 2017/4/15.
	 * @param index ��ҳ�洫����±�
	 * @param modelMap ҳ��ӳ�����
	 * @return ���ȴ���ҳ��
	 */
	@RequestMapping(value = "/view_schedule_detail{index}", method = RequestMethod.GET)
	public String showSchedule(@PathVariable("index") int index, ModelMap modelMap) {
		modelMap.addAttribute("beginDate", dateMessage.beginDate);
		modelMap.addAttribute("endDate", dateMessage.endDate);
		modelMap.addAttribute("size", dateMessage.size);
		modelMap.addAttribute("timeInterval", dateMessage.timeInterval);

		modelMap.addAttribute("list", statisticDatas.get(index).getRequestStopDatas());
		return "/view_schedule_detail";
	}

	/**
	 * @desc ����ͳ��������
	 * @author Created by lizhongwei on 2017/4/15.
	 * @param modelMap ҳ��ӳ�����
	 * @return ����ͳ����������ҳ��
	 */
	@RequestMapping(value = "/fee_statistics_condition", method = RequestMethod.GET)
	public String showFeeStatisticsCond(ModelMap modelMap) {
		return "fee_statistics_condition";
	}

	/**
	 * @desc ����ͳ��������
	 * @author Created by lizhongwei on 2017/4/15.
	 * @param request ����
	 * @param response ����
	 * @param modelMap ҳ��ӳ�����
	 * @return ����ͳ�ƽ��ҳ��
	 */
	@RequestMapping(value = "/fee_statistics", method = RequestMethod.GET)
	public String showFeeStatistics(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		statisticDatas.clear();
		String date = request.getParameter("beginDate");
		modelMap.addAttribute("beginDate", date);
		int d1[] = {3, 1, 3, 0};
		int d2[] = {0, 2, 1, 0};
		int d3[] = {2, 3, 4, 0};
		int cnt[];
		if (date.equals("2017-06-10")) {
			cnt = d1;
		}
		else if (date.equals("2017-06-11")) {
			cnt = d2;
		}
		else {
			cnt = d3;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date bd = null;
		Date ed = null;
		try {
			bd = sdf.parse(date + " " + "00:00:00");
			ed = sdf.parse(date + " " + "23:59:59");
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		int windTime[] = new int[3];
		double energyConsume = 0;
		double fee = 0;
		List<RequestStopData> requestStopDatas = new ArrayList<>();
		List<OnOffStatistic> onOffList = new ArrayList<OnOffStatistic>();
		StatisticData data;

		for (int i = 1; i <= 4; i++) {
			onOffList.clear();
			int lowFee = 0;
			int middleFee = 0;

			onOffList.addAll(onOffStatisticRepository.find(i, bd, ed));
			for (OnOffStatistic e : onOffList) {
				ArrayList<RequestStopStatistic> tmpList = (ArrayList<RequestStopStatistic>) requestStopStatisticRepository.find(e.getKeyId(), e.getPowerOnTime(), e.getPowerOffTime());
				for (int k = 0; k < tmpList.size(); ++k) {
					Date requestTime = tmpList.get(k).getRequestTime();
					Date stopTime = tmpList.get(k).getStopTime();
					RequestStopData rsd = new RequestStopData(requestTime, stopTime, tmpList.get(k).getRequestLevelWind(),(stopTime.getTime() - requestTime.getTime())/1000);
					requestStopDatas.add(rsd);
				}
			}

			for (OnOffStatistic e : onOffList) {
				windTime[0] += e.getLowLevelWindTime();
				windTime[1] += e.getMiddleLevelWindTime();
				windTime[2] += e.getHighLevelWindTime();
				lowFee += ceil(e.getLowLevelWindTime(), 3);
				middleFee += ceil(e.getMiddleLevelWindTime(), 2);
				fee += e.getTotalFee();
			}
			energyConsume += windTime[0]*0.75 + windTime[1] + windTime[2]*1.25;

			data = new StatisticData(date, onOffList.size(), onOffList.size(),
					windTime[0], windTime[1], windTime[2], requestStopDatas.size(), energyConsume, fee, requestStopDatas);
			windTime[0] = 0;
			windTime[1] = 0;
			windTime[2] = 0;
			energyConsume = 0;
			fee = 0;
			data.setTargetCnt(cnt[i - 1]);
			statisticDatas.add(data);

			modelMap.addAttribute("lowFee" + i, lowFee);
			modelMap.addAttribute("middleFee" + i, middleFee);
			modelMap.addAttribute("highFee" + i, data.getHighWindTime());
			modelMap.addAttribute("totalFee" + i, data.getTotalFee());
			modelMap.addAttribute("scheduleCnt" + i, data.getScheduleCnt());
			modelMap.addAttribute("lowTime" + i, data.getLowWindTime());
			modelMap.addAttribute("middleTime" + i, data.getMiddleWindTime());
			modelMap.addAttribute("highTime" + i, data.getHighWindTime());
			requestStopDatas.clear();
		}

		modelMap.addAttribute("list", statisticDatas);


		return "fee_statistics";
	}

	/**
	 * @desc ������־������
	 * @author Created by lizhongwei on 2017/4/15.
	 * @param modelMap ҳ��ӳ�����
	 * @return ������־ҳ��
	 */
	@RequestMapping(value = "/table_control_log", method = RequestMethod.GET)
	public String showHostLog(ModelMap modelMap) {
		ArrayList<HostOperationLog> hostOperationLogs = (ArrayList<HostOperationLog>) hostOperationLogRepository.findAll();
		ArrayList<HostStateLog> hostStateLogs = (ArrayList<HostStateLog>) hostStateLogRepository.findAll();

		if (hostOperationLogs.size() != 0)
			modelMap.addAttribute("opLogList", hostOperationLogs);
		if (hostStateLogs.size() != 0)
			modelMap.addAttribute("stateLogList", hostStateLogs);
		return "/table_control_log";
	}

	/**
	 * @desc �ӻ���־������
	 * @author Created by lizhongwei on 2017/4/15.
	 * @param modelMap ҳ��ӳ�����
	 * @return �ӻ���־ҳ��
	 */
	@RequestMapping(value = "/table_slave_log", method = RequestMethod.GET)
	public String showSlaveLog(ModelMap modelMap) {
		ArrayList<SlaveOperationLog> slaveOperationLogs = (ArrayList<SlaveOperationLog>) slaveOperationLogRepository.findAll();
		ArrayList<SlaveStateLog> slaveStateLogs = (ArrayList<SlaveStateLog>) slaveStateLogRepository.findAll();

		if (slaveOperationLogs.size() != 0)
			modelMap.addAttribute("opLogList", slaveOperationLogs);
		if (slaveStateLogs.size() != 0)
			modelMap.addAttribute("stateLogList", slaveStateLogs);
		return "/table_slave_log";
	}

	/**
	 * @desc ϵͳ����ҳ������
	 * @author Created by lizhongwei on 2017/4/15.
	 * @param modelMap ҳ��ӳ�����
	 * @return ϵͳ��������ҳ��
	 */
	@RequestMapping(value = "/system_set", method = RequestMethod.GET)
	public String showSystemSet(ModelMap modelMap) {
		String checked = "checked=\"\"";
		String selected = "selected=\"\"";


		if (mode) {
			modelMap.addAttribute("cooling", checked);
		}
		else {
			modelMap.addAttribute("heating", checked);
		}

		if (default_wind == 1) {
			modelMap.addAttribute("lowSelect", selected);
		}
		else if (default_wind == 2) {
			modelMap.addAttribute("middleSelect", selected);
		} else if (default_wind == 3) {
			modelMap.addAttribute("highSelect", selected);
		}
		modelMap.addAttribute("lowT", temp_min);
		modelMap.addAttribute("highT", temp_max);
		modelMap.addAttribute("targetT", target_temp);

		return "system_set";
	}

	/**
	 * @desc ϵͳ��������
	 * @author Created by lizhongwei on 2017/4/15.
	 * @param modelMap ҳ��ӳ�����
	 * @return ϵͳ��������ҳ��
	 */
	@RequestMapping(value="/systemSet", method = RequestMethod.POST)
	public String systemSet(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		mode = Boolean.parseBoolean(request.getParameter("mode"));
		temp_min = Integer.parseInt(request.getParameter("temp_min"));
		temp_max = Integer.parseInt(request.getParameter("temp_max"));
		default_wind = Integer.parseInt(request.getParameter("wind_speed"));
		target_temp = Integer.parseInt(request.getParameter("target_temp"));

        String checked = "checked=\"\"";
		String selected = "selected=\"\"";

		String content = Run.cmpAttri(temp_max, temp_min, mode, target_temp, default_wind);

		hostOperationLogRepository.saveAndFlush(new HostOperationLog(new Date(), content, "bupt"));

		if (mode) {
			modelMap.addAttribute("cooling", checked);
		}
		else {
			modelMap.addAttribute("heating", checked);
		}

		if (default_wind == 1) {
			modelMap.addAttribute("lowSelect", selected);
		}
		else if (default_wind == 2) {
			modelMap.addAttribute("middleSelect", selected);
		} else if (default_wind == 3) {
			modelMap.addAttribute("highSelect", selected);
		}

		modelMap.addAttribute("lowT", temp_min);
		modelMap.addAttribute("highT", temp_max);
		modelMap.addAttribute("targetT", target_temp);

		if (temp_max > temp_min && (target_temp < temp_max && target_temp > temp_min))
			Run.setAttri(temp_max, temp_min, mode, target_temp, default_wind);

		return "system_set";
	}

}