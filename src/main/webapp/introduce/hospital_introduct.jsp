<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>병원소개</title>
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/initStyle.css"> --%>
<%-- 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/header.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/footer.css"> --%>
	
	<!-- 병원소개 css -->
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/introduce/hospital_introduct.css">
	<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/body.css"> --%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/index.css">
	

<body>

<%-- <jsp:include page="/header.jsp" /> --%>
	
	<!-- <div class="container"> -->
		<section>
	<div id="side">
		<div id="side_box"><img src="${pageContext.request.contextPath}/images/introduct/병원장.png" style="width:250px; height:250px;"></div>
		<div id="map"></div>

	<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b09bcb7ac760f677621481b06167e2d0"></script>
	<script>

		// 지도에 마커를 생성하고 표시한다
		/*
		var marker = new kakao.maps.Marker({
		    position: new kakao.maps.LatLng(37.56682, 126.97865), // 마커의 좌표
		    draggable : false, // 마커를 드래그 가능하도록 설정한다
		    map: map // 마커를 표시할 지도 객체
		});
		*/
		//지도에 마커 생성 표시
		var marker = {
			position: new kakao.maps.LatLng(37.56682, 126.97865), // 마커의 좌표
		}
		
		//지도 설정
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new kakao.maps.LatLng(37.56682, 126.97865), // 지도의 중심좌표
	        level: 7, // 지도의 확대 레벨
	        mapTypeId : kakao.maps.MapTypeId.ROADMAP, // 지도종류
	        marker : marker
	    }; 
		
		// 지도를 생성한다 
		var map = new kakao.maps.StaticMap(mapContainer, mapOption); 

		//지도 영역 레이아웃 재설정 함수
		function relayout() {    
		    
		    // 지도를 표시하는 div 크기를 변경한 이후 지도가 정상적으로 표출되지 않을 수도 있습니다
		    // 크기를 변경한 이후에는 반드시  map.relayout 함수를 호출해야 합니다 
		    // window의 resize 이벤트에 의한 크기변경은 map.relayout 함수가 자동으로 호출됩니다
		    map.relayout();
		}
		
		//▼아래 요소는 정적 지도가 아닌 경우 사용▼
		
		// 지도 타입 변경 컨트롤을 생성한다
		//var mapTypeControl = new kakao.maps.MapTypeControl();

		// 지도의 상단 우측에 지도 타입 변경 컨트롤을 추가한다
		//map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);	

		// 지도에 확대 축소 컨트롤을 생성한다
		//var zoomControl = new kakao.maps.ZoomControl();

		// 지도의 우측에 확대 축소 컨트롤을 추가한다
		//map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

		// 지도 중심 좌표 변화 이벤트를 등록한다
		/*
		kakao.maps.event.addListener(map, 'center_changed', function () {
			console.log('지도의 중심 좌표는 ' + map.getCenter().toString() +' 입니다.');
		});

		// 지도 확대 레벨 변화 이벤트를 등록한다
		kakao.maps.event.addListener(map, 'zoom_changed', function () {
			console.log('지도의 현재 확대레벨은 ' + map.getLevel() +'레벨 입니다.');
		});

		// 지도 영역 변화 이벤트를 등록한다
		kakao.maps.event.addListener(map, 'bounds_changed', function () {
			var mapBounds = map.getBounds(),
				message = '지도의 남서쪽, 북동쪽 영역좌표는 ' +
							mapBounds.toString() + '입니다.';

			console.log(message);	
		});

		// 지도 시점 변화 완료 이벤트를 등록한다
		kakao.maps.event.addListener(map, 'idle', function () {
			var message = '지도의 중심좌표는 ' + map.getCenter().toString() + ' 이고,' + 
							'확대 레벨은 ' + map.getLevel() + ' 레벨 입니다.';
			console.log(message);
		});

		// 지도 클릭 이벤트를 등록한다 (좌클릭 : click, 우클릭 : rightclick, 더블클릭 : dblclick)
		kakao.maps.event.addListener(map, 'click', function (mouseEvent) {
			console.log('지도에서 클릭한 위치의 좌표는 ' + mouseEvent.latLng.toString() + ' 입니다.');
		});	

		// 지도 드래깅 이벤트를 등록한다 (드래그 시작 : dragstart, 드래그 종료 : dragend)
		kakao.maps.event.addListener(map, 'drag', function () {
			var message = '지도를 드래그 하고 있습니다. ' + 
							'지도의 중심 좌표는 ' + map.getCenter().toString() +' 입니다.';
			console.log(message);
		});
		*/
	</script>
	</div>
	<div id="mid_content">
		
		<h1>안녕하십니까?</h1>
			<h1><strong>율제병원 원장 주전입니다.</strong></h1>
		<p>
		
		<hr size="6" width="50px" align="left" color="blue">
			<strong>중증 고난도 질환중심의 첨단 지능형 병원을 구축하겠습니다.</strong><br>
우리 병원은 중증 고난도 희귀, 난치성 질환에 있어 압도적인 위상을 확립해 나아갈 예정이며, 이를 뒷받침할 4차 산업혁명에 맞춘 첨단 지능형 병원의 의료환경 구축을 위해 노력하고 있습니다.
현재 중증치료 역량 강화를 위한 ‘R&D허브’, ‘진료 운영자원의 통합관리’, ‘산·학·연·병의 메디컬 클러스터 구축’ 등을 가속화하여 진료 흐름을 예측하고 활동에 반영할 수 있는 체계를 마련하는 등 진료지원 프로세스를 더욱 견고히 해 나아가고 있습니다. 이를 통해, 단순히 질병의 치료 성적인 ‘Clinical Outcome’ 뿐만 아니라, 환자를 토탈 케어하여 육체와 정신의 완전한 회복을 목표로 하는 ‘Patient Outcome’까지 관리하여 중증환자의 치료와 삶의 질까지 책임지는 병원이 되겠습니다.
특히, 병원 전반의 리모델링을 통해 미래병원 구축을 위한 최신 정보화 네트워크인 SDN(Software Defined Network) 도입 등 ‘하드웨어 혁신’과 스마트 병실과 병상, 원격 회진 등 디지털 기술을 활용한 ‘소프트웨어 혁신’ 노력을 가속화함으로서, 고난도 중증 진료에 최적화된 첨단 의료환경을 구축해 나가겠습니다.
</p>
<p>
<hr size="6" width="50px" align="left" color="blue">
<strong>감염병 위험에 대비하여 ‘넥스트노멀’을 준비하고 환자가 안전한 환경에서 진료받을 수 있도록 최선을 다하겠습니다.</strong><br>
전세계적으로 감염병 사태를 겪고 있으며 앞으로도 우리 사회는 감염병 위험으로부터 자유롭지 못한 것이 현실입니다. 우리 병원은 더욱 엄격한 방역체계를 구축하고 예측불가능한 미래상황에 대해 능동적으로 대처할 수 있는 프로세스를 갖춘 ‘넥스트노멀’을 준비하겠습니다.
우리 병원은 모든 환자분들이 감염병의 위험에서 벗어나 중증치료에 전념할 수 있는 선도적 인프라와 시스템을 구축하고 있습니다. 또한, 이를 위해 우리 병원의 모든 의료진은 방역지침을 철저히 준수할 것을 약속드립니다.
</p>
<p>
<hr size="6" width="50px" align="left" color="blue">
<strong>의료기관에 맞는 ESG 경영을 구현하겠습니다.</strong><br>
사회와 환경을 고려하는 ESG 경영을 의료현실에 맞게 재정립하여 ‘친환경 병원’으로 거듭나겠습니다.
이를 위해 수열 에너지, 지역난방 등 에너지 사용 효율을 향상시키고, 노후 열원 설비 교체 등을 통한 온실가스 배출량 감소 및 의료 폐기물 감소 노력과 함께, 병원내 녹지공간의 효율적인 재구성으로 ‘자연 친화적 병원’을 조성하는데 주력하겠습니다. 우리 병원은 ‘안전한 근무환경’을 구축하고 다양한 협력업체들과의 거래에서 준법 및 투명경영 실천으로 모두가 함께 하는 상생경영 기반을 마련하도록 하겠습니다.
이와 함께, 협력병원과 진료 프로세스를 공유하는 시스템을 차별성 있게 발전시켜, 우리 병원만의 독자적 진료협력체계 구축에 선도적 역량을 발휘하겠습니다.
</p>
<p>
<hr size="6" width="50px" align="left" color="blue">
<strong>끝으로, 항상 환자를 최우선으로 하는 환자중심 병원 문화 및 직원간 상호존중하는 수평적 문화를 더욱 공고히 하겠습니다.</strong><br>
우리 병원은 대한민국 의료 패러다임의 혁신 주체로서 현재 고객서비스에 집중된 ‘환자중심의 의료 문화’를 다시 한번 변화시키고자 합니다. 환자중심 가치의 실현을 위해 맞춤치료를 통한 치료성과의 극대화를 위해 다방면으로 노력하며 지역병원과의 의학적 협력체계를 더욱 공고히 하여 환자의 불편을 최소화 하기 위해 노력 중입니다.
이와 함께, 우리 병원의 모든 구성원은 다 같이 환자 진료에 참여하는 케어기버라는 인식을 바탕으로, 우리 병원 곳곳에 수평적 조직문화가 뿌리내리게 하여 모든 케어기버가 행복하게 근무하는 병원을 만들겠습니다. 전 케어기버가 세대를 아울러서 한마음 한뜻으로 의료계에 새로운 패러다임을 확립하여 사회로부터 신뢰받고 대한민국 의료계와 함께 성장하는 훌륭한 병원이 되도록 노력하겠습니다.
</p>
<p>
<hr size="6" width="50px" align="left" color="blue">
삼성서울병원의 모든 케어기버는 ‘미래 의료의 중심 SMC’의 실현을 위해 앞으로도 최선을 다하겠습니다.
</p>
<p>
감사합니다.
</p>
	</div>
	
	</section>
	<!-- </div> -->
	<%-- <jsp:include page="/footer.jsp" /> --%>
</body>
</html>