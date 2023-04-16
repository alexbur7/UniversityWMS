package ru.alexbur.feature.scanner.di

import dagger.Component
import ru.alexbur.core.di.named.FeatureScope
import ru.alexbur.core.domain.manager.CommonComponentManager
import ru.alexbur.core.domain.mediators.CommonComponentMediator
import ru.alexbur.feature.scanner.presentation.ScannerViewModel

@FeatureScope
@Component(
    dependencies = [CommonComponentMediator::class],
    modules = [ScannerModule::class]
)
interface ScannerComponent {

    fun getViewModel(): ScannerViewModel

    @Component.Builder
    interface Builder {
        fun commonComponent(commonComponentMediator: CommonComponentMediator): Builder
        fun build(): ScannerComponent
    }

    companion object {

        @Volatile
        private var component: ScannerComponent? = null

        fun getComponent(): ScannerComponent =
            component ?: synchronized(this) {
                component ?: DaggerScannerComponent.builder()
                    .commonComponent(CommonComponentManager.component)
                    .build()
                    .also { component = it }
            }
    }
}