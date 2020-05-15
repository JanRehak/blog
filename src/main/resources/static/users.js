document.addEventListener("DOMContentLoaded", () => {
    loadUsers();
    loadRoles();
    document.getElementsByName('mainForm')[0].addEventListener('submit', event => {
    	event.preventDefault();
    	storeUser(
    			document.mainForm.username.value, 
    			document.mainForm.password.value,
    			Array.from(document.mainForm.roles.selectedOptions).map(o => o.value)
		);
    	return false;
    });
    document.getElementsByName('mainForm2')[0].addEventListener('submit', event => {
        event.preventDefault();
        removeUser(
                document.mainForm2.id.value
        );
        return false;
    });
    document.getElementsByName('mainForm3')[0].addEventListener('submit', event => {
        event.preventDefault();
        editUser(
                document.mainForm3.id.value,
                document.mainForm3.name.value,
                document.mainForm3.password.value
        );
        return false;
    });
});

const storeUser = (username, password, roleIds) => {
    const req = new XMLHttpRequest();
    req.addEventListener('load', loadUsers);
    req.open("POST", "./api/users");
    req.setRequestHeader('Content-Type', 'application/json');
    //    token not working
//    req.setRequestHeader('X-CSRF-TOKEN', 'token');
    const newUser = {
        name: username,
        password: password,
        roles: roleIds.map(roleId => ({ id: roleId }))
    };
    req.send(JSON.stringify(newUser));
};

const editUser = (id, username, password) => {
    const req = new XMLHttpRequest();
    req.addEventListener('put', editUser);
    req.open("PUT", "./api/users/" + id);
    req.setRequestHeader('Content-Type', 'application/json');
    const updatedUser = {
            name: username,
            password: password
        };
    req.send(JSON.stringify(updatedUser));
};

const removeUser = (id) => {
    const req = new XMLHttpRequest();
    req.addEventListener('delete', removeUser);
    req.open("DELETE", "./api/users/" + id);
    req.send();
};

const loadRoles = () => {
    const req = new XMLHttpRequest();
    req.addEventListener('load', () => {
        const rolesSelect = document.getElementById('role-selector');
        rolesSelect.innerHTML = '';
        const roles = JSON.parse(req.responseText);
        roles.forEach(role => {
        	const roleOption = document.createElement('option');
        	roleOption.value = role.id;
        	roleOption.innerText = role.name;
        	rolesSelect.append(roleOption);
        });
    });
    req.open("GET", "./api/roles");
    req.send();
};

const loadUsers = () => {
    const req = new XMLHttpRequest();
    req.addEventListener('load', () => {
        const tableBody = document.getElementById('user-table');
        tableBody.innerHTML = '';
        const users = JSON.parse(req.responseText);
        users.forEach(user => createRow(tableBody, user.id, user.name, user.roles));
    });
    req.open("GET", "./api/users");
    req.send();
};

const createRow = (tableBody, id, name, roles) => {
    const idCell = document.createElement('td');
    idCell.innerText = id;
    const nameCell = document.createElement('td');
    nameCell.innerText = name;
    const rolesCell = document.createElement('td');
    roles.forEach(role => { par = document.createElement('p');
    par.innerText = role.name;
    rolesCell.append(par);
    })
    const userRow = document.createElement('tr');
    userRow.append(idCell, nameCell, rolesCell);
    tableBody.append(userRow);
};
