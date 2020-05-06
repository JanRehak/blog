document.addEventListener("DOMContentLoaded", () => {
	loadArticles();
});


const loadArticles = () => {
    const req = new XMLHttpRequest();
    req.addEventListener('load', () => {
        const tableBody = document.getElementById('article-list');
        tableBody.innerHTML = '';
        const articles = JSON.parse(req.responseText);
        articles.content.forEach(article => createRow(tableBody, article));
    });
    req.open("GET", "./api/articles/pageable?page=0&size=3&sort=id,desc");
    req.send();
};

const createRow = (tableBody, article) => {
    const nameCell = document.createElement('td');
    nameCell.innerText = article.author.name;
    const contentCell = document.createElement('td');
    const contentParagraph = document.createElement('p');
    contentParagraph.innerText = article.content;
    const commentsTable = document.createElement('table');
    contentCell.append(contentParagraph, commentsTable);
    const articleRow = document.createElement('tr');
    articleRow.append(nameCell, contentCell);
    tableBody.append(articleRow);
};
