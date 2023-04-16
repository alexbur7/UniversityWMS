package ru.alexbur.feature.scanned_data.di

import dagger.Component
import ru.alexbur.core.di.named.FeatureScope
import ru.alexbur.core.domain.manager.CommonComponentManager
import ru.alexbur.core.domain.mediators.CommonComponentMediator
import ru.alexbur.feature.scanned_data.presentation.ScannedDataViewModel

@FeatureScope
@Component(
    dependencies = [CommonComponentMediator::class],
    modules = [ScannedDataModule::class]
)
interface ScannedDataComponent {

    fun getViewModel(): ScannedDataViewModel

    @Component.Builder
    interface Builder {
        fun commonComponent(commonComponentMediator: CommonComponentMediator): Builder
        fun build(): ScannedDataComponent
    }

    companion object {

        @Volatile
        private var component: ScannedDataComponent? = null

        fun getComponent(): ScannedDataComponent =
            component ?: synchronized(this) {
                component ?: DaggerScannedDataComponent.builder()
                    .commonComponent(CommonComponentManager.component)
                    .build()
                    .also { component = it }
            }
    }
}