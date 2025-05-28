package com.example.securingweb.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.securingweb.demo.model.UserDto;
import com.example.securingweb.demo.service.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

import java.util.List;

/**
 * MVCモデル
 * 
 * controller (@controller) : HTTP通信でやり取り
 * model (@Entity) : Beanとして使用
 * service (@service) : ロジックにあたる
 * repository (@repository) : DBとやり取りをする、例外処理などを自動で行ってくれる
 * 
 * 依存関係 : @controller → @service → @repository (→　@Entity)
 * 
 * springBootの特徴DIコンテナを使用: @Entityなどのアノテーションを記載すると
 * DIコンテナと呼ばれる場所にオブジェクトが保管される。
 * それを@Autwiredで簡易的に生成してしようすることが可能
 * 
 */


@Controller
@RequestMapping("/search")
public class SearchController {

/**
 * コンストラクタインジェクションはfinal修飾子を使用できるため安全性が高い
 * またどのクラスに依存関係があるのか分かりやすい。
 * フィールドインジェクションはあくまで簡易的なもの。
 *     
    private final SearchService searchService;
    public SearchController(SearchService searchService) {
    this.searchService = searchService;
    }
*/
    @Autowired
    private SearchService searchService;

    @GetMapping
    public String search(@ModelAttribute UserDto form, Model model) {
        // リクエストのオブジェクトをserviceに引数として生成する
        List<UserDto> userList = searchService.searchItems(form);
        model.addAttribute("userList", userList);

        return "index";
    }
}