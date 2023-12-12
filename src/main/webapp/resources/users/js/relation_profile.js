var buttonModRe = document.getElementById('modify-relation');
var controlButton = document.getElementById('showControl');
var text = buttonModRe.innerText;
var block_button = document.getElementById('block-button')
var block_confirm = document.getElementById('block-confirm')

if (text.includes("Đã gửi lời mời kết bạn")) {
	buttonModRe.addEventListener('click', showCancelFriendInvitationControl)
} else if (text.includes("Đang chờ phản hồi kết bạn")) {
	buttonModRe.addEventListener('click', showRejectAcceptFriendInvitationControl)
} else if (text.includes("Kết bạn")) {
	buttonModRe.addEventListener('click', addFriend)
} else if (text.includes("Đã kết bạn")) {
	buttonModRe.addEventListener('click', confirmRemoveFriend)
}

block_button.addEventListener('click', confirmBlockUser)

function confirmBlockUser() {
	if (block_confirm.innerHTML == "") {
		block_confirm.innerHTML = `<p>Xác nhận block?</p> <button id="confirm-block-action">Có</button><p>Bạn sẽ được chuyển hướng tới trang chủ</p>`;
		var yesButton = document.getElementById("confirm-block-action");
		yesButton.addEventListener('click', blockUser)
	} else {
		block_confirm.innerHTML = "";
	}
}

function blockUser() {
	$.ajax({
		type: "PUT",
		url: "/FinalProject/api/relationship",
		contentType: "application/json",
		data: JSON.stringify({
			"relationshipID": relation_id,
			"fromUserID": current_user_id,
			"toUserID": profile_user_id,
			"friend": 0,
			"pending": 0,
			"blocking": 1
		}),
		success: function(response) {
			if (response != "Failed") {
				var newURL = window.location.origin + "/FinalProject/trang-chu"
				window.location.href = newURL;
			}
		},
		error: function(error) {
			console.log("Error:", error);
		},
	});
}

function confirmRemoveFriend() {
	if (controlButton.innerHTML == "") {
		controlButton.innerHTML = `<div>Hủy kết bạn?</div> <button id="remove-friend">Có</button>`;
		var yesButton = document.getElementById("remove-friend");
		yesButton.addEventListener('click', removeFriend)
	} else {
		controlButton.innerHTML = "";
	}
}
function removeFriend() {
	$.ajax({
		type: "DELETE",
		url: "/FinalProject/api/relationship",
		contentType: "application/json",
		data: JSON.stringify({
			"relationshipID": relation_id
		}),
		success: function(response) {
			$.ajax({
				type: "DELETE",
				url: "/FinalProject/api/relationship",
				contentType: "application/json",
				data: JSON.stringify({
					"relationshipID": op_relation_id
				}),
				success: function(response) {
					console.log(response);
					buttonModRe.innerText = "Kết bạn";
					buttonModRe.removeEventListener('click', confirmRemoveFriend)
					buttonModRe.addEventListener('click', addFriend)
					controlButton.innerHTML = ""
				},
				error: function(error) {
					console.log("Error:", error);
				},
			});
		},
		error: function(error) {
			console.log("Error:", error);
		},
	});
}
function addFriend() {
	$.ajax({
		type: "POST",
		url: "/FinalProject/api/relationship",
		contentType: "application/json",
		data: JSON.stringify({
			"fromUserID": current_user_id,
			"toUserID": profile_user_id,
			"friend": 0,
			"pending": 1,
			"blocking": 0
		}),
		success: function(response) {
			if (response != "Failed") {
				console.log(typeof response)
				relation_id = parseInt(response);
				buttonModRe.innerText = "Đã gửi lời mời kết bạn";
				buttonModRe.removeEventListener('click', addFriend)
				buttonModRe.addEventListener('click', showCancelFriendInvitationControl)
				controlButton.innerHTML = ""
			}
		},
		error: function(error) {
			console.log("Error:", error);
		},
	})
}
function showRejectAcceptFriendInvitationControl() {
	if (controlButton.innerHTML == "") {
		controlButton.innerHTML = `<div>Từ chối lời mời?</div> <button id="reject-invitation">Có</button>
                  <div>Đồng ý lời mời?</div> <button id="accept-invitation">Có</button>`;
		var rejcetButton = document.getElementById('reject-invitation');
		var acceptButton = document.getElementById('accept-invitation');
		rejcetButton.addEventListener('click', rejectFriendInvitation)
		acceptButton.addEventListener('click', acceptFriendInvitation)
	} else {
		controlButton.innerHTML = "";
	}
}
function rejectFriendInvitation() {
	$.ajax({
		type: "DELETE",
		url: "/FinalProject/api/relationship",
		contentType: "application/json",
		data: JSON.stringify({
			"relationshipID": op_relation_id
		}),
		success: function(response) {
			console.log(response);
			buttonModRe.innerText = "Kết bạn";
			buttonModRe.removeEventListener('click', showRejectAcceptFriendInvitationControl)
			buttonModRe.addEventListener('click', addFriend)
			controlButton.innerHTML = ""
		},
		error: function(error) {
			console.log("Error:", error);
		},
	});
}
function acceptFriendInvitation() {
	console.log(current_user_id + '-' + profile_user_id)
	$.ajax({
		type: "PUT",
		url: "/FinalProject/api/relationship",
		contentType: "application/json",
		data: JSON.stringify({
			"relationshipID": op_relation_id,
			"fromUserID": profile_user_id,
			"toUserID": current_user_id,
			"friend": 1,
			"pending": 0,
			"blocking": 0
		}),
		success: function(response) {
			console.log(response);
			if (response != "Failed") {
				$.ajax({
					type: "PUT",
					url: "/FinalProject/api/relationship",
					contentType: "application/json",
					data: JSON.stringify({
						"relationshipID": relation_id,
						"fromUserID": current_user_id,
						"toUserID": profile_user_id,
						"friend": 1,
						"pending": 0,
						"blocking": 0
					}),
					success: function(response) {
						console.log(response);
						buttonModRe.innerText = "Đã kết bạn";
						buttonModRe.removeEventListener('click', showRejectAcceptFriendInvitationControl)
						buttonModRe.addEventListener('click', confirmRemoveFriend)
						controlButton.innerHTML = ""
					},
					error: function(error) {
						console.log("Error:", error);
					},
				});
			}
		},
		error: function(error) {
			console.log("Error:", error);
		},
	});

}

function showCancelFriendInvitationControl() {
	if (controlButton.innerHTML == "") {
		controlButton.innerHTML = `<div>Hủy lời mời?</div> <button id="cancel-invitation">Có</button>`;
		var yesButton = document.getElementById("cancel-invitation");
		yesButton.addEventListener('click', cancelFriendInvitation)
	} else {
		controlButton.innerHTML = "";
	}
}
function cancelFriendInvitation() {
	$.ajax({
		type: "DELETE",
		url: "/FinalProject/api/relationship",
		contentType: "application/json",
		data: JSON.stringify({
			"relationshipID": relation_id,
		}),
		success: function(response) {
			console.log(response);
			buttonModRe.innerText = "Kết bạn";
			buttonModRe.removeEventListener('click', showCancelFriendInvitationControl)
			buttonModRe.addEventListener('click', addFriend);
			controlButton.innerHTML = ""
		},
		error: function(error) {
			console.log("Error:", error);
		},
	});
}