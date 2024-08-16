$(function() { //html화면이 열리면 function함수가 동작 준비를 함 =>$(document).ready(function(){});과 같
		$("#calender").datepicker(
				{
					changeMonth : true,
					changeYear : true,
					nextText : '다음 달',
					prevText : '이전 달',
					dayNames : [ '일요일', '월요일', '화요일', '수요일', '목요일', '금요일',
							'토요일' ],
					dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
					monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월',
							'7월', '8월', '9월', '10월', '11월', '12월' ],
					monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
							'8월', '9월', '10월', '11월', '12월' ],
					dateFormat : "y/mm/dd",
					maxDate: 0, //선택 가능한 최소날짜, 0: 오늘 이후 선택 불가
					/* onClose : function(selectedDate) {
						//시작일(startDate) datepicker가 닫힐때                      
						//종료일(endDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정                     
						$("#endDate").datepicker("option", "minDate",
								selectedDate);
					} */
				});
	});