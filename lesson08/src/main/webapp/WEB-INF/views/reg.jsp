<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta charset="UTF-8" />
<link href="style.css" rel="stylesheet" />
</head>

<body>

	<form action='' method="post">
		<table border='1'>
			<tr>
				<td width='50' align='center'>Login</td>
				<td width='50' align='center'><input type='text' name='login'
					value=''></td>
			</tr>

			<tr>
				<td width='50' align='center'>Name</td>
				<td width='50' align='center'><input type='text' name='name' />
				</td>
			</tr>

			<tr>
				<td width='50' align='center'>Password</td>
				<td width='50' align='center'><input type='password'
					name='password' /></td>
			</tr>

			<tr>
				<td width='50' align='center'>Re-Password</td>
				<td width='50' align='center'><input type='password'
					name='re-password' /></td>
			</tr>

			<tr>
				<td width='50' align='center'>Age</td>
				<td width='50' align='center'><input type="number" name='age' />
				</td>
			</tr>

			<tr>
				<td width='50' align='center'>Gender</td>
				<td width='50' align='center'>F <input type='radio'
					name='gender' value='F'> M <input type='radio'
					name='gender' value='M'>
				</td>
			</tr>

			<tr>
				<td width='50' align='center'>Address</td>
				<td width='50' align='center'><select name='address'>
						<option value='1'></option>
						<option value='1'>DNR</option>
						<option value='2'>LNR</option>
						<option value='3'>Crimea</option>
				</select></td>
			</tr>

			<tr>
				<td width='50' align='center'>Comments</td>
				<td width='50' align='center'><textarea name='comments'
						cols='20' rows='10'></textarea></td>
			</tr>

			<tr>
				<td width='50' align='center'>Amigo</td>
				<td width='50' align='center'><input type='checkbox'
					name='amigo' /></td>
			</tr>

			<tr>
				<td width='50' align='center'></td>
				<td width='50' align='center'><input type='submit' value='OK' />
				</td>
			</tr>

		</table>
	</form>

</body>
</html>