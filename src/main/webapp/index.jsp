<%@ include file="/common/taglibs.jsp"%>

<div class="col-sm-7">
    <div id="intro">
        <p style="margin:35px 0 0 10px;font-size: large">Towards making world, a better place to live with the hope of having real democracy.</p>
<%--
         <p>
            <button class="btn btn-primary" onclick="location.href='users'">View Calls</button>
        </p>
--%>
        <h2 style="font-style: italic">Mission:</h2>
        <h3>Reforestation and Watershed Management</h3>
        <ul>
            <li>Purpose is to educate and activate people how reforestation will solve the problems of ‘Water table’ going deep and causing concentration of minerals especially ‘fluorine’ in drinking water. Educate people in Watershed management, to improve water table and stop soil erosion. This work constitutes approximately 25% of the group’s activities. Most of the work is planned to be accomplished by volunteer staff initially
                </li>
            <li> Educating people in this field will also stop mass migration to urban areas in search of livelihood. It is observed that most of the people are migrating due to failure of crops, on which they depend</li>
            <li>Rainwater harvesting and storage in a manner that vapor-transpiration losses are minimized</li>
        </ul>
        <h3>Renewable energy sources</h3>
        <ul>
            <li>Encourage usage of Solar power, Wind power</li>
            <li>Making renewable energy sources, tools equipment affordable</li>
        </ul>
    </div>
</div>
<div class="col-sm-3">
<div id="branding">
    <a href="http://ashakiran.org" title="AshaKiran">
        <img src="${ctx}/images/tagore5.gif" width="140" height="147" alt="AppFuse"/>
    </a>
    <ul>
        <li>Where the mind is without fear and the head is held high;</li>
        <li>Where knowledge is free;</li>
        <li>Where the world has not been broken up into fragments by narrow domestic walls;</li>
        <li>Where the clear stream of reason has not lost its way into the dreary desert sand of dead habit;</li>
        <li>Into that heaven of freedom, my Father, let my country awake</li>
    </ul>

</div>
</div>
<script type="text/javascript">
function readMore() {
    var main = document.getElementById("intro");
    var more = document.getElementById("readmore");
    if (main.style.display == "") {
        main.style.display = "none";
        more.style.display = "";
    } else {
        more.style.display = "none";
        main.style.display = "";
    }
}
</script>
