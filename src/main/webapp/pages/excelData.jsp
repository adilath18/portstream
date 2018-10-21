<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
<h3>Imported Data</h3>
<table id="importedData" class="table table-striped table-bordered" style="width:100%">
	<thead>
		<tr>
			<td></td>
			<td>Imo</td>
			<td>﻿Capacity-Dwt</td>
			<td>Vessel Name</td>
			<td>Destination Port</td>
			<td>Destination Port Country</td>
			<td>ETA</td>
			<td>Origin Port</td>
			<td>Origin Port Country</td>
			<td>Draught</td>
			<td>Load Condition</td>
			<td>Previous To Origin Port</td>
			<td>Previous To Origin Port Country</td>
			<td>Current Port Country</td>
			<td>Commercial Market</td>
			<td>Commercial Size Class</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${vesselData}" var="vessel" varStatus="status">
		<tr>
			<td>${status.index}</td>
			<td>${vessel.imo}</td>
			<td>${vessel.capacityDWT}</td>
			<td>${vessel.name}</td>
			<td>${vessel.portCall.destinationPort.portName}</td>
			<td>${vessel.portCall.destinationPort.portCountry}</td>
			<td>${vessel.portCall.eta}</td>
			<td>${vessel.portCall.originPort.portName}</td>
			<td>${vessel.portCall.originPort.portCountry}</td>
			<td>${vessel.draught}</td>
			<td>${vessel.loadCondition.condition}</td>
			<td>${vessel.portCall.previousOriginPort.portName}</td>
			<td>${vessel.portCall.previousOriginPort.portCountry}</td>
			<td>${vessel.portCall.currentPortCountry}</td>
			<td>${vessel.commercialMarket.marketType}</td>
			<td>${vessel.commercialMarket.sizeClass}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

</div>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script>
$(document).ready(function(){
	$("#importedData").DataTable();
})
</script>