<%@ page contentType="text/html;charset=UTF-8" %>

<div style="position: absolute; top: 0; right: 0;">
    <p>Balance: ${user.balance}</p>

    <button onclick="document.getElementById('replenishDialog').showModal()">
        Пополнить баланс
    </button><br>
    <c:if test="${param.error == 1}">
        <span style="color: darkred">Пустое значение</span>
    </c:if>
    <c:if test="${param.error == 2}">
        <span style="color: darkred">Отрицательное значение</span>
    </c:if>

    <dialog id="replenishDialog">
        <form method="post" action="${pageContext.request.contextPath}/balance">
            <h3>Выберите сумму пополнения</h3>

            <input type="radio" name="amount" value="500">
            <label for="r500">500 ₽</label><br>

            <input type="radio" name="amount" value="1000">
            <label for>1000 ₽</label><br>

            <input type="radio" id="r1500" name="amount" value="1500">
            <label for="r1500">1500 ₽</label><br>

            <label for="custom">Другая сумма:</label>
            <input type="number" name="amount"><br><br>
            <menu>
                <button type="submit">Пополнить</button>
                <button type="button" onclick="document.getElementById('replenishDialog').close()">
                    Отмена
                </button>
            </menu>
        </form>
    </dialog>
</div>