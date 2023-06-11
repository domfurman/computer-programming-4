//const a = 5;
//const foo = () => {console.log("Hello World!")};



const getProducts = () => {
    return fetch("/api/products")
        .then(response => response.json());
}

const getCurrentOffer = () => {
    return fetch("/api/get-current-offer")
        .then(response => response.json())
}

//const refreshOffer = async () => {
//    let offer = await getCurrentOffer();
//    let cart = document.querySelector('.cart');
//    cart.querySelector('.total').textContent = `${offer.total} PLN`;
//    cart.querySelector('.itemsCount').textContent = `${offer.itemsCount} items`;
//
//}

const refreshOffer = async () => {
    var offer = await getCurrentOffer();
    var json = JSON.parse(offer);
    var cart = document.getElementByClassName('cart')[0];
    cart.getElementsByClassName('total')[0].textContent = `${json.total} PLN`;
    cart.getElementsByClassName('itemsCount')[1].textContent = json.itemsCount;
}

const createHtmlFromString = (htmlAsString) => {
    const tmpElem = document.createElement('div');
    tmpElem.innerHTML = htmlAsString.trim();
    return tmpElem.firstChild;
}

const createHtmlComponent = (product) => {
    const template = `
        <li class="product">
            <h4>${product.name}</h4>
            <img />
            <span>${product.price}</span>
            <button
                class="product__add-to-cart"
                data-product-id="${product.id}"
            >
                Add to cart
            </button>
        </li>
    `;
    return createHtmlFromString(template);
}

const addToCart = (productId) => {
    return fetch("/api/add-to-cart/{productId}", {
        method: 'POST'
    });
};

const initializeAddToCartHandler = (htmlEl) => {
    const btn = htmlEl.querySelector('button.product__add-to-cart');
    btn.addEventListener('click', () => {
        addToCart(btn.getAttribute('data-product-id'))
            .then(refreshOffer());
    });
    return htmlEl;
};

(async () => {
    const productsListEl = document.querySelector('#products-list');
    await refreshOffer();
    const products = await getProducts();
    products
        .map(product => createHtmlComponent(product))
        .map(productComponent => initializeAddToCartHandler(productComponent))
        .forEach(el => productsListEl.appendChild(el));
})();