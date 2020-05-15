//初始化MarkDown编辑器
var contentEditor;
$(function () {
    contentEditor = editormd("editor", {
        width: "100%",
        height: 640,
        syncScrolling: "single",
        path: "/lib/editormd/lib/",
        placeholder: '请输入内容',
        toolbarIcons: function () {
            return ["undo", "redo", "|", "bold", "del", "italic", "quote", "ucwords", "lowercase", "|", "h1", "h2", "h3", "h4", "h5", "|", "list-ul", "list-ol", "hr", "|", "link", "reference-link", "image", "code", "table", "datetime", "html-entities", "|", "goto-line", "watch", "preview", "fullscreen", "clear"]
        }
    });
});