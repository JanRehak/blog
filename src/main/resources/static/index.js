document.addEventListener("DOMContentLoaded", () => {
	loadArticles();
	loadArticlesPeriodically();
});


const loadArticles = () => {
        const req = new XMLHttpRequest();
        req.addEventListener('load', () => {
            const tableBody = document.getElementById('article-list');
            tableBody.innerHTML = '';
            const articles = JSON.parse(req.responseText);
            articles.content.forEach(article => createRow(tableBody, article));
        });
        req.open("GET", "./api/articles/pageable?page=0&size=3&sort=modifiedDateTime,desc");
        req.send();
    };

const createRow = (tableBody, article) => {
    const nameCell = document.createElement('td');
    nameCell.innerText = article.author.name;
    const contentCell = document.createElement('td');
    const contentParagraph = document.createElement('p');
    const idCell = document.createElement('td');
    idCell.innerText = `id:${article.id}`;
    contentParagraph.innerText = article.content;
    const commentsTable = document.createElement('table');
    contentCell.append(contentParagraph, commentsTable);
    const articleRow = document.createElement('tr');
    articleRow.append(nameCell, contentCell, idCell);
    tableBody.append(articleRow);
};

const loadArticlesPeriodically = () => { setInterval( function loadArticles() {
    const req = new XMLHttpRequest();
    req.addEventListener('load', () => {
        const tableBody = document.getElementById('article-list');
        tableBody.innerHTML = '';
        const articles = JSON.parse(req.responseText);
        articles.content.forEach(article => createRow(tableBody, article));
    });
    req.open("GET", "./api/articles/pageable?page=0&size=3&sort=modifiedDateTime,desc");
    req.send();
    },5000)
};