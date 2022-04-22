package com.localbakery.authentication.service;

import com.localbakery.account.domain.Account;
import com.localbakery.authentication.oauth2.OAuth2UserInfo;
import com.localbakery.authentication.oauth2.OAuth2UserInfoFactory;
import com.localbakery.authentication.oauth2.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final AccountService accountService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        try {
            return processOAuthUser(userRequest, oAuth2User);
        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
        }
    }

    /**
     * 사용자 정보 추출
     */
    private OAuth2User processOAuthUser(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory
                .getOAuth2UserInfo(userRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());

        Optional<Account> userOptional = accountService.findByEmail(oAuth2UserInfo.getEmail());
        Account account;

        if (userOptional.isPresent()) {
            account = userOptional.get();
        } else {
            account = registerNewUser(userRequest, oAuth2UserInfo);
        }
        return UserPrincipal.create(account);
    }

    //DB에 없을 때, 등록
    private Account registerNewUser(OAuth2UserRequest userRequest, OAuth2UserInfo oAuth2UserInfo) {
        return accountService.saveAccount(
                Account.builder()
                        .userName(oAuth2UserInfo.getName())
                        .email(oAuth2UserInfo.getEmail())
                        .build()
        );
    }
}