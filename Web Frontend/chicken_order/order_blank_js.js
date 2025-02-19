window.onload = function () {
  // 주문 버튼 클릭 시 실행되는 이벤트 리스너 추가
  document.getElementById('orderBtn').addEventListener('click', function () {

    /*
        참고
        parseInt            : 문자열을 정수로 변환
        textContent         : 요소의 텍스트 내용을 가져오거나 설정
        innerHTML           : 요소의 HTML 콘텐츠를 가져오거나 설정
        forEach()           : 배열 또는 배열과 유사한 객체에서 각 요소를 반복 처리
        nextElementSibling  : 현재 요소의 다음 형제 요소를 반환
        createElement()     : 지정한 태그 이름의 새로운 HTML 요소를 생성
        appendChild()       : 부모 노드의 마지막 자식으로 특정 노드를 추가
        options[]           : <select> 요소의 모든 <option> 요소를 배열처럼 반환
        selectedIndex       : <select> 요소에서 선택된 옵션의 인덱스를 반환하거나 설정
        ... 등
    */

    // code 작성

    let chickenType = document.querySelector('#chickenType');
    let quantity = document.querySelector('#quantity');
    let error = document.querySelector('#error');
    let extraOption = document.getElementsByName('extraOption');
    let deliveryType = document.getElementsByName('deliveryType');
    let totalPrice = 0;

    if (chickenType.value == "18000")
      document.querySelector('#receiptChickenType').innerHTML = '후라이드 치킨 (18,000원)';
    else if (chickenType.value == "19000")
      document.querySelector('#receiptChickenType').innerHTML = '양념 치킨 (19,000원)';
    else if (chickenType.value == "20000")
      document.querySelector('#receiptChickenType').innerHTML = '반반 치킨 (20,000원)';
    else {
      error.innerHTML = '치킨 종류를 선택하세요.';
      return false;
    }

    if (quantity.value < 1) {
      error.innerHTML = '수량은 1이상이어야 합니다.';
      return false;
    } else
      document.querySelector('#receiptQuantity').innerHTML = quantity.value + '개';

    totalPrice = parseInt(chickenType.value) * parseInt(quantity.value);


    let extra = ``;
    for (let i = 0; i < extraOption.length; i++) {
      if (extraOption[i].checked) {
        extra += `${extraOption[i].parentNode.textContent}<br>`;
        totalPrice += parseInt(extraOption[i].value);
      }
    }
    document.querySelector('#receiptExtras').innerHTML = extra;

    for (let i = 0; i < deliveryType.length; i++) {
      if (deliveryType[i].checked) {
        document.querySelector('#receiptDelivery').innerHTML = deliveryType[i].parentNode.textContent;
        totalPrice += parseInt(deliveryType[i].value);
      }
    }

    document.querySelector('#totalPriceDisplay').innerHTML = '총 결제 금액: ' + totalPrice.toLocaleString() + '원';
    // 모달 창 열기
    receiptModal.style.display = 'flex';

  });

  // 모달 창 닫기 버튼 클릭 시 모달 창 닫기
  document.getElementById('closeModal').addEventListener('click', function () {
    document.getElementById('receiptModal').style.display = 'none'; // 모달 창 숨기기
  });
};
