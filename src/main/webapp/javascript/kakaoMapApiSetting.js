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
//resize 이벤트 리스너
window.onresize = function(){
	//지도를 표시하는 div 크기를 변경한 이후 지도가 정상적으로 표출되지 않을 수도 있습니다
    //크기를 변경한 이후에는 반드시  map.relayout 함수를 호출해야 합니다 
    //window의 resize 이벤트에 의한 크기변경은 map.relayout 함수가 자동으로 호출됩니다
	//지도 영역 레이아웃 재설정 함수
	map.relayout();
	map.getCenter();
};