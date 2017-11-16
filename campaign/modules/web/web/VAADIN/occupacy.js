         var oauthToken = null;

            function login() {
                var userLogin = $('#loginField').val();
                var userPassword = $('#passwordField').val();
                $.post({
                    url: 'http://localhost:8080/app/rest/v2/oauth/token',
                    headers: {
                        'Authorization': 'Basic Y2xpZW50OnNlY3JldA==',
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    dataType: 'json',
                    data: {grant_type: 'password', username: userLogin, password: userPassword},
                    success: function (data) {
                        oauthToken = data.access_token;
                        $('#loggedInStatus').show();
                        $('#loginForm').hide();
                        printTestMessage();
                    }
                })
            }
function getOccupacy(){
           var webSite =  $('#website-criteria').val();
             var startDate = $('#start_date').val();
             var endDate = $("#end_date").val();
               var urlStr= 'http://localhost:8080/app/rest/v2/services/campaign_CampaignService/generateWebSiteOccupacy?start='+startDate
                  +'&end='+ endDate + '&website=' + webSite;
     $.get({
                        url: urlStr,
                        headers: {
                            'Authorization': 'Bearer ' + oauthToken,
                            'Content-Type': 'application/x-www-form-urlencoded'
                        },
                        success: function (data) {
                        $('#input-content').css('visibility','visible');
                        $('#timeline-content').show();
                        $('#start_date').text(''+startDate);
                        $('#end_date').text(''+endDate);
                          $.each(data, function (i, occupacy) {
                                $('.days').append('<li class="day"><div class="events"> <p class="occupacy_content">'+ occupacy +'</p> <div>'+ dayOfWeek + '</div> </div> </li>');
                            });
                        }
                    });
                 }
		function checkInput() {
				var regex = {
					date : /^\d{2}\/\d{2}\/\d{4}/,
					website : /^[A-Z]{1}[a-z]+$/,
				};
				var emplFirstName = $('#employeeFirstName').val();
				var emplLastName = $('#employeeLastName').val();
				var age = $('#age').val();
				var lengthOfService = $('#lengthOfService').val();
				var errorStr ='';
					if (!regex['employeeFirstName'].test(emplFirstName)) {
						errorStr+="Error input employee first name \n";
					}
					if (!regex['employeeLastName'].test(emplLastName)) {
						errorStr+="Error  input employee last name \n";
					}
					if (!regex['age'].test(age)) {
						errorStr+="Error input employee age \n";
					}
					if (!regex['lengthOfService'].test(lengthOfService)) {
						errorStr+="Error input employee length of service \n";
					}

					return errorStr;
			}