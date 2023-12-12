var list_date_time = document.querySelectorAll('.comment_date, .description, .chat_date, .time_date')
for (let time of list_date_time) {
	// Lấy nội dung text của mỗi đối tượng
	var textValue = time.innerText;
	// Chuyển đổi text thành đối tượng Date
	var dateObject = new Date(textValue);
	// Kiểm tra xem chuyển đổi thành công hay không
	if (!isNaN(dateObject.getTime())) {
		// Chuyển đổi thành công, bạn có thể làm gì đó với đối tượng Date ở đây
		var formattedDate = new Intl.DateTimeFormat('en-US', {
			year: 'numeric',
			month: 'long',
			day: 'numeric',
			hour: 'numeric',
			minute: 'numeric',
			second: 'numeric',
			timeZone: 'UTC' // Điều chỉnh múi giờ theo cần thiết
		}).format(dateObject);

		// Gán lại giá trị text đã được định dạng trở lại cho đối tượng
		time.innerText = formattedDate;
	} else {
		// Xử lý trường hợp không thể chuyển đổi thành công
		console.log('Không thể chuyển đổi giá trị: ' + textValue);
	}
}