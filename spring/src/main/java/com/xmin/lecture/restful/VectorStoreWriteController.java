package com.xmin.lecture.restful;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class VectorStoreWriteController {

    final VectorStore vectorStore;

    public static final String CONTENT = """
            xmin 无代码平台帮助文档
            
            欢迎使用 xmin！本平台旨在帮助您轻松创建和管理应用程序，无需编写任何代码。无论您是初学者还是经验丰富的开发者，xmin 都能提供高效、灵活的工具，让您专注于业务需求而不是技术实现。
            
            1. 介绍
            xmin 是一个无代码应用构建平台，它使您能够通过可视化界面和简单的拖放操作创建各种应用，包括网站、移动应用、自动化工具等。无论您是要搭建一个简单的表单，还是构建一个复杂的工作流，xmin 都能满足您的需求。
            
            2. 功能概述
            可视化编辑器：直观的界面，支持拖放组件，轻松设计页面布局。
            内置模板：丰富的模板库，涵盖常见的业务场景，节省您的构建时间。
            数据管理：无需编写SQL，您可以在平台上直接管理数据库表格和记录。
            自动化工作流：支持自动化任务和工作流配置，简化日常操作。
            API集成：支持与其他系统的API对接，无需开发，轻松集成。
            发布与部署：一键部署您的应用，支持多种平台。
            3. 如何开始
            3.1 注册与登录
            访问 xmin 官网，点击 注册 按钮，填写您的基本信息。
            登录您的帐户，进入您的个人工作空间。
            3.2 创建新项目
            登录后，点击左侧导航栏的 创建新项目 按钮。
            选择一个合适的模板，或从空白页面开始。
            输入项目名称，并点击 开始构建。
            3.3 使用可视化编辑器
            在编辑器中，您可以通过拖拽组件（如按钮、文本框、图片等）来构建页面。
            点击组件，在右侧面板中调整样式、行为和内容。
            使用左侧的 布局 面板来调整页面结构，例如添加/删除行和列。
            3.4 配置数据源
            进入 数据管理 面板，点击 创建新数据表。
            输入数据表名称并定义字段。
            使用 数据绑定 功能，将数据表与页面组件关联，实现动态数据展示。
            3.5 设置工作流
            进入 工作流管理 页面，点击 创建工作流。
            选择一个触发条件（如按钮点击、表单提交等）。
            配置工作流中的操作（如发送邮件、更新数据、调用API等）。
            3.6 发布与部署
            在右上角点击 发布 按钮。
            选择您的发布目标平台（如Web、iOS、Android）。
            点击 一键部署，系统将自动为您生成部署包。
            """;

    @GetMapping("/vec/write")
    public String chatWithRole() {
        vectorStore.write(Stream.of(CONTENT.split("。")).map(Document::new).toList());
        return "success";
    }

}
