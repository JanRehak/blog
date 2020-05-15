document.addEventListener("DOMContentLoaded", () => {
	loadArticles();
    document.getElementsByName('mainForm')[0].addEventListener('submit', event => {
    	event.preventDefault();
    	storeArticle(document.mainForm.content.value);
    	return false;
    });
    document.getElementsByName('mainForm2')[0].addEventListener('submit', event => {
        event.preventDefault();
        removeArticle(
                document.mainForm2.id.value
        );
        return false;
    });
    document.getElementsByName('mainForm3')[0].addEventListener('submit', event => {
        event.preventDefault();
        editArticle(
                document.mainForm3.id.value,
                document.mainForm3.content.value,
        );
        return false;
    });
});

const storeArticle = (_content) => {
    const req = new XMLHttpRequest();
    req.addEventListener('load', loadArticles);
    req.open("POST", "./api/articles");
    req.setRequestHeader('Content-Type', 'application/json');
    const newArticle = {
    	content: _content
    };
    req.send(JSON.stringify(newArticle));
};

const loadArticles = () => {
    const req = new XMLHttpRequest();
    req.addEventListener('load', () => {
        const tableBody = document.getElementById('article-list');
        tableBody.innerHTML = '';
        const articles = JSON.parse(req.responseText);
        articles.forEach(article => createRow(tableBody, article));
    });
    req.open("GET", "./api/articles");
    req.send();
};

const createRow = (tableBody, article) => {
    const nameCell = document.createElement('td');
    nameCell.innerText = article.author.name;
    const timeCell = document.createElement('td');
    timeCell.innerText = article.modifiedDateTime;
    const idCell = document.createElement('td');
    idCell.innerText = `id:${article.id}`;
    const contentCell = document.createElement('td');
    const contentParagraph = document.createElement('p');
    contentParagraph.innerText = article.content;
    const commentsTable = document.createElement('table');
    contentCell.append(contentParagraph, commentsTable);
    const articleRow = document.createElement('tr');
    articleRow.append(nameCell,  contentCell, timeCell, idCell);
    tableBody.append(articleRow);
};

const removeArticle = (id) => {
    console.log(id);
    const req = new XMLHttpRequest();
    req.addEventListener('delete', removeArticle);
    req.open("DELETE", "./api/articles/" + id);
    req.send();
};

const editArticle = (id, content) => {
    const req = new XMLHttpRequest();
    req.addEventListener('put', editArticle);
    req.open("PUT", "./api/articles/" + id);
    req.setRequestHeader('Content-Type', 'application/json');
    const updatedArticle = {
            content: content,
        };
    req.send(JSON.stringify(updatedArticle));
};


