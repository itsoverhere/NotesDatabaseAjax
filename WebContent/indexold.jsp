<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Notes</title>

	<style>
		body{
			width:100%;
			background-color:#adc;
			padding:20px;
		}
	
		.note{
			width:50%;
			margin:auto;
			margin-top:10px;
			transition-property:all;
			transition-duration:2s;
		}
		
		.note:hover{
			border-shadow:0px 1px rgba(30,30,30, 0.5);
		}
	
		.note-header{
			background-color:#444;
			color:white;
			height:20px;
		}
		
		.note .note-content{
			color:#444;
			background-color:white;
			padding:20px;
			font-family:sans-serif;
			
		}
		
		form input[type=text]{
			padding:20px;
			width:100%;
			box-sizing:border-box;
			display:block;
		}
		
		form#addNoteForm{
			width:50%;
			margin:auto;
			position:relative;
			display:block;
			height:100px;
		}
		
		form input[type=submit]{
			padding:15px;
			padding-top:7px;
			padding-bottom:7px;
			background-color:#444;
			color:#fff;
			margin-top:5px;
			border:none;
			position:absolute;
			right:0px; 
			display:block;
		}
		
	</style>
	
	<script src="jquery-2.1.1.js"></script>
	
	<script>
	
		$(document).ready(function(){
			$(".note-header").click(function(){
				$("#idnote").val($(this).attr("id"));
				$("#toggleNoteForm").submit();
			});
		});
		
	</script>

</head>

<body>

	<form action="toggle" id="toggleNoteForm" method="POST">
		<input type="hidden" id="idnote" name="idnote" />
	</form>
	
	<form action="add" id="addNoteForm" method="POST">
		<input type="text" name="note" id="noteinput" placeholder="Hey, what's up?" />
		<input type="submit" id="postbutton" value="post"  />
	</form>

	<div id="notebox">
		<c:forEach items="${notes}" var="note">
			<div class="note">
				<div class="note-header" id="${note.id}"></div>
				
				<div class="note-content <c:if test="${!note.visible}">hidden</c:if>">
					${note.note}
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>