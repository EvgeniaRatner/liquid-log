



<html>

<head>
    <title>SD40 Performance indicator</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<script src="/js/jquery-3.1.1.min.js"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/css/bootstrap.min.css"
          integrity="sha384-AysaV+vQoT3kOAXZkl02PThvDr8HYKPZhNT5h/CXfBThSRXQ6jW5DO2ekP5ViFdi" crossorigin="anonymous"/>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"
            integrity="sha384-BLiI7JTZm+JWlgKa0M0kGRpJbF2J8q+qreVrKBC47e3K6BW78kGLrCkeRX6I9RoK"
            crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.2.0/js/tether.min.js"
        integrity="sha384-Plbmg8JY28KFelvJVai01l8WyZzrYWG825m+cZ0eDDS1f7d/js6ikvy1+X+guPIB"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.17.1/moment.min.js"></script>
    <link rel="stylesheet" href="/css/style.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker3.css"/>

</head>

<body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript">
    	function setupModal(client){
    		$('#formFrom').val(moment().format("DD/MM/YYYY"))
    		$('#formTo').val(moment().format("DD/MM/YYYY"))
    		$('#formTo').datepicker({
    			format: "dd/mm/yyyy"
    		})
    		$('#formFrom').datepicker({
    			format: "dd/mm/yyyy",
    		})

    		$('#formMaxResults').val(100)
    		$('#customForm').attr('action','/history/'+client+'/custom')
    		console.log(moment().format('zz'))
    	}
    </script>
<div class="container">
	<br>
	<div class="alert alert-info">
  		<h3><strong>Attention!</strong><br>All requests for stored data are made with UTC time.<br>Requested data will be displayed in your browsers timezone.</h3>
	</div>
	<br>
    <h2 >Client list</h2>
    <table class="table table-striped table-fixed"> <!-- table-bordered  -->
        <thead class="thead-inverse">
            <th class="col-xs-6">Name</th>
            <th class="col-xs-6">Link</th>
        </thead>
         <tbody>

            <tr>
                <td class="col-xs-6">
                    <h4><span>sampledb</span></h2>
                </td>
                <td class="col-xs-6">
                	<a class="btn btn-outline-primary" href='/history/sampledb/2017/9'>Previous Month</a>
                    <a class="btn btn-outline-primary" href='/history/sampledb/2017/10'>Month</a>
                    <a class="btn btn-outline-primary" href='/history/sampledb?count=2016'>Last 7 days</a>
                    <a class="btn btn-outline-primary" href='/history/sampledb?count=864'>Last 3 days</a>
                    <a class="btn btn-outline-primary" href='/history/sampledb/2017/10/29'>Yesterday</a>
                    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#customModal" onclick="setupModal('sampledb')">Custom request</button>
                </td>
            </tr>

            <tr>
                <td class="col-xs-6">
                    <h4><span>_internal</span></h2>
                </td>
                <td class="col-xs-6">
                	<a class="btn btn-outline-primary" href='/history/_internal/2017/9'>Previous Month</a>
                    <a class="btn btn-outline-primary" href='/history/_internal/2017/10'>Month</a>
                    <a class="btn btn-outline-primary" href='/history/_internal?count=2016'>Last 7 days</a>
                    <a class="btn btn-outline-primary" href='/history/_internal?count=864'>Last 3 days</a>
                    <a class="btn btn-outline-primary" href='/history/_internal/2017/10/29'>Yesterday</a>
                    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#customModal" onclick="setupModal('_internal')">Custom request</button>
                </td>
            </tr>

            <tr>
                <td class="col-xs-6">
                    <h4><span>sdng</span></h2>
                </td>
                <td class="col-xs-6">
                	<a class="btn btn-outline-primary" href='/history/sdng/2017/9'>Previous Month</a>
                    <a class="btn btn-outline-primary" href='/history/sdng/2017/10'>Month</a>
                    <a class="btn btn-outline-primary" href='/history/sdng?count=2016'>Last 7 days</a>
                    <a class="btn btn-outline-primary" href='/history/sdng?count=864'>Last 3 days</a>
                    <a class="btn btn-outline-primary" href='/history/sdng/2017/10/29'>Yesterday</a>
                    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#customModal" onclick="setupModal('sdng')">Custom request</button>
                </td>
            </tr>

            <tr>
                <td class="col-xs-6">
                    <h4><span>parsing</span></h2>
                </td>
                <td class="col-xs-6">
                	<a class="btn btn-outline-primary" href='/history/parsing/2017/9'>Previous Month</a>
                    <a class="btn btn-outline-primary" href='/history/parsing/2017/10'>Month</a>
                    <a class="btn btn-outline-primary" href='/history/parsing?count=2016'>Last 7 days</a>
                    <a class="btn btn-outline-primary" href='/history/parsing?count=864'>Last 3 days</a>
                    <a class="btn btn-outline-primary" href='/history/parsing/2017/10/29'>Yesterday</a>
                    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#customModal" onclick="setupModal('parsing')">Custom request</button>
                </td>
            </tr>

        </tbody>
        </table>
</div>

<div class="modal fade" id="customModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class=modal-dialog role="dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">Select dates and max results</h4>
			</div>
			<form id="customForm">
				<div class="modal-body">
				    <div class="row">
				        <div class="col-md-6">
                            <div class="form-group">
                                <label for="formFrom">From</label>
                                <input type="text"class="form-control" id="formFrom" name="from">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="formTo">To</label>
                                <input type="text" class="form-control" id="formTo" name="to">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="formCount">Max results</label>
                        <input class="form-control" type="number" value="42" id="formMaxResults" name="maxResults">
                    </div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-success">Request</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>
			</form>
		</div>
	</div>
</div>

<div class="form">
  <h1>Run log parser</h1>
   <form action="${pageContext.request.contextPath}/parser" method="POST">
        <div class="field-wrap">
          <input type="text" required name="influx" placeholder = "Influx Database">
        </div>

        <div class="field-wrap">
            <input type="text" required name="filepath" placeholder = "File path">
        </div>

        <div class="field-wrap">
            <input type="text" required name="timezone" placeholder = "Timezone">
        </div>

        <div class="field-wrap">
            <select size="3" style = "width: 100%;vertical-align: middle;" name = "mode">
               <option disabled>Parse mode</option>
               <option value="sdng">sdng</option>
               <option selected value="gc">gc</option>
               <option value="top">top</option>
              </select>
        </div>

        <div class="tab-group">
           <p style="color:grey; padding-left: 35%; font-size: 25px;">Trace result</p>
           <input type="checkbox" style = "transform:scale(1.3); opacity:0.9; cursor:pointer;" name= "trace">
        </div>
      <button type="submit" class="button button-block"/>Run</button>
   </form>
</body>

</html>