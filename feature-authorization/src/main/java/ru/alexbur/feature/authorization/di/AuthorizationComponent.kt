package ru.alexbur.feature.authorization.di

import dagger.Component
import ru.alexbur.core.di.named.FeatureScope
import ru.alexbur.core.domain.manager.CommonComponentManager
import ru.alexbur.core.domain.mediators.CommonComponentMediator
import ru.alexbur.feature.authorization.presentation.AuthorizationViewModel

@FeatureScope
@Component(
    dependencies = [CommonComponentMediator::class],
    modules = [AuthorizationModule::class]
)
interface AuthorizationComponent {

    fun getViewModel(): AuthorizationViewModel

    @Component.Builder
    interface Builder {
        fun commonComponent(commonComponentMediator: CommonComponentMediator): Builder
        fun build(): AuthorizationComponent
    }

    companion object {

        @Volatile
        private var component: AuthorizationComponent? = null

        fun getComponent(): AuthorizationComponent =
            component ?: synchronized(this) {
                component ?: DaggerAuthorizationComponent.builder()
                    .commonComponent(CommonComponentManager.component)
                    .build()
                    .also { component = it }
            }
    }
}