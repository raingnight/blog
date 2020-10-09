function loadImg(classname){
    let prefix = 'https://cdn.jsdelivr.net/gh/raingnight/Blog_Pic/yinlu/yinlu_'
    let suffix = '.jpg'
    let imgs = $('.'+classname);
    for (let i = 0; i < imgs.length; i++) {
        imgs[i].src=prefix+Math.floor(Math.random()*112+1)+suffix
    }
}