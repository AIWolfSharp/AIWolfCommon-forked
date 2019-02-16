/**
 * RequestContentBuilder.java
 * 
 * Copyright (c) 2016 人狼知能プロジェクト
 */
package org.aiwolf.client.lib;

import java.util.ArrayList;
import java.util.Arrays;

import org.aiwolf.common.data.Agent;

/**
 * <div lang="ja">要求発話ビルダークラス</div>
 * 
 * <div lang="en">Builder class for the utterance of a request.</div>
 * 
 * @author otsuki
 *
 */
public class RequestContentBuilder extends ContentBuilder {

	/**
	 * <div lang="ja">他エージェントの行動を要求するためのRequestContentBuilderを構築する</div>
	 *
	 * <div lang="en">Constructs a RequestContentBuilder to request for the other agent's action.</div>
	 * 
	 * @param target
	 *            <div lang="ja">要求先のエージェント</div>
	 *
	 *            <div lang="en">The requested agent.</div>
	 * @param content
	 *            <div lang="ja">要求される行動を表す{@code Content}</div>
	 *
	 *            <div lang="en">{@code Content} representing the requested action.</div>
	 */
	public RequestContentBuilder(Agent target, Content content) {
		this(Agent.UNSPEC, target, content);
	}

	/**
	 * <div lang="ja">他エージェントの行動を要求するためのRequestContentBuilderを構築する</div>
	 *
	 * <div lang="en">Constructs a RequestContentBuilder to request for the other agent's action.</div>
	 * 
	 * @param subject
	 *            <div lang="ja">要求をするエージェント</div>
	 *
	 *            <div lang="en">The agent who requests.</div>
	 * @param target
	 *            <div lang="ja">要求先のエージェント</div>
	 *
	 *            <div lang="en">The requested agent.</div>
	 * @param content
	 *            <div lang="ja">要求される行動を表す{@code Content}</div>
	 *
	 *            <div lang="en">{@code Content} representing the requested action.</div>
	 */
	public RequestContentBuilder(Agent subject, Agent target, Content content) {
		topic = Topic.OPERATOR;
		operator = Operator.REQUEST;
		this.subject = subject;
		this.target = target;
		if (this.target == Agent.UNSPEC) {
			this.target = content.getSubject() == Agent.UNSPEC ? Agent.ANY : content.getSubject();
		}
		contentList = new ArrayList<>(Arrays.asList(content));
	}

	@Override
	String getText() {
		return ContentBuilder.join(" ", new String[] {
				subject == Agent.UNSPEC ? "" : subject.toString(),
				operator.toString(),
				target.toString(),
				"(" + (contentList.get(0).getSubject() == target ? Content.stripSubject(contentList.get(0).getText()) : contentList.get(0).getText()) + ")"
		}).trim();
	}

}
