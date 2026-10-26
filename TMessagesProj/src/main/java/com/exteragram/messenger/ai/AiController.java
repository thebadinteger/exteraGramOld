package com.exteragram.messenger.ai;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.exteragram.messenger.ai.data.Role;
import com.exteragram.messenger.ai.data.Service;
import com.exteragram.messenger.ai.data.Suggestions;
import com.exteragram.messenger.ai.network.backend.OnDeviceAvailability;
import com.exteragram.messenger.utils.chats.ChatUtils;
import com.google.android.gms.cast.MediaError;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;

public class AiController {
    private final List<Role> roles = new ArrayList();
    private final List<Service> services = new ArrayList();

    public static class SingletonHolder {
        private static final AiController INSTANCE = new AiController();
    }

    public static boolean isOnDeviceError(int i) {
        return i == -1 || i == -2 || i == -3 || i == -4 || i == -5;
    }

    public AiController() {
        loadRoles();
        loadServices();
    }

    public static void clearHistory(BaseFragment baseFragment, Theme.ResourcesProvider resourcesProvider, boolean z) {
        clearHistory(baseFragment, resourcesProvider, z, null);
    }

    public static void clearHistory(final BaseFragment baseFragment, Theme.ResourcesProvider resourcesProvider, boolean z, final Runnable runnable) {
        if (baseFragment == null) {
            return;
        }
        if (z) {
            AlertDialog.Builder builder = new AlertDialog.Builder(baseFragment.getParentActivity(), resourcesProvider);
            builder.setMessage(AndroidUtilities.replaceTags(LocaleController.getString(R.string.ClearConversationHistoryInfo)));
            builder.setTitle(LocaleController.getString(R.string.ClearHistory));
            builder.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
            builder.setPositiveButton(LocaleController.getString(R.string.ClearButton), new AlertDialog.OnButtonClickListener() {
                @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
                public final void onClick(AlertDialog alertDialog, int i) {
                    AiController.m1029$r8$lambda$k4Ykokjerf7UwZXWV7hhEULXCk(runnable, baseFragment, alertDialog, i);
                }
            });
            AlertDialog alertDialogCreate = builder.create();
            baseFragment.showDialog(alertDialogCreate);
            TextView textView = (TextView) alertDialogCreate.getButton(-1);
            if (textView != null) {
                textView.setTextColor(Theme.getColor(Theme.key_text_RedBold));
                return;
            }
            return;
        }
        AiConfig.clearConversationHistory();
        if (runnable != null) {
            runnable.run();
        }
        BulletinFactory.of(baseFragment).createSimpleBulletin(R.raw.ic_delete, LocaleController.getString(R.string.HistoryCleared)).show();
    }

    public static void m1029$r8$lambda$k4Ykokjerf7UwZXWV7hhEULXCk(Runnable runnable, BaseFragment baseFragment, AlertDialog alertDialog, int i) {
        AiConfig.clearConversationHistory();
        if (runnable != null) {
            runnable.run();
        }
        BulletinFactory.of(baseFragment).createSimpleBulletin(R.raw.ic_delete, LocaleController.getString(R.string.HistoryCleared)).show();
    }

    public static void showErrorBulletin(ViewGroup viewGroup, Theme.ResourcesProvider resourcesProvider, int i, String str, Runnable runnable) {
        showErrorBulletin(null, viewGroup, resourcesProvider, i, str, runnable);
    }

    public static void showErrorBulletin(BaseFragment baseFragment, int i, String str) {
        showErrorBulletin(baseFragment, null, null, i, str, null);
    }

    private static void showErrorBulletin(BaseFragment baseFragment, ViewGroup viewGroup, final Theme.ResourcesProvider resourcesProvider, final int i, String str, final Runnable runnable) {
        int i2;
        int i3;
        final int i4;
        int i5;
        final Context context;
        String strTrimErrorMessage;
        BulletinFactory bulletinFactoryGlobal;
        boolean z = false;
        if (i == -5) {
            i2 = R.string.AIErrorOnDeviceStopped;
            i3 = R.string.AIErrorOnDeviceStoppedInfo;
        } else {
            int i6 = 2;
            if (i == -4) {
                i2 = R.string.AIErrorOnDeviceTooLong;
                i5 = R.string.AIErrorOnDeviceTooLongInfo;
            } else {
                if (i == -3) {
                    i2 = R.string.AIErrorOnDeviceDownloading;
                    i5 = R.string.AIErrorOnDeviceDownloadingInfo;
                } else {
                    if (i == -2) {
                        i2 = R.string.AIErrorOnDeviceDownload;
                        i5 = R.string.AIErrorOnDeviceDownloadInfo;
                    } else if (i == -1) {
                        i2 = R.string.AIErrorOnDevice;
                        i3 = R.string.AIErrorOnDeviceInfo;
                    } else if (i == 204) {
                        i2 = R.string.AIErrorEmpty;
                        i5 = R.string.AIErrorEmptyInfo;
                    } else {
                        if (i == 408) {
                            i2 = R.string.AIError408;
                            i5 = R.string.AIError408Info;
                        } else if (i == 429) {
                            i2 = R.string.AIError429;
                            i5 = R.string.AIError429Info;
                            i6 = 6;
                        } else if (i == 502) {
                            i2 = R.string.AIError502;
                            i3 = R.string.AIError502Info;
                        } else if (i != 503) {
                            switch (i) {
                                case MediaError.DetailedErrorCode.MANIFEST_UNKNOWN /* 400 */:
                                    i2 = R.string.AIError400;
                                    i5 = R.string.AIError400Info;
                                    break;
                                case 401:
                                    i2 = R.string.AIError401;
                                    i3 = R.string.AIError401Info;
                                    break;
                                case 402:
                                    i2 = R.string.AIError402;
                                    i5 = R.string.AIError402Info;
                                    break;
                                case 403:
                                    i2 = R.string.AIError403;
                                    i5 = R.string.AIError403Info;
                                    i6 = 9;
                                    break;
                                default:
                                    i2 = R.string.AIError;
                                    i4 = 3;
                                    i5 = R.string.AIErrorInfo;
                                    z = true;
                                    break;
                            }
                        } else {
                            i2 = R.string.AIError503;
                            i3 = R.string.AIError503Info;
                        }
                        context = null;
                        if (!z || i == 400 || i == 403) {
                            strTrimErrorMessage = trimErrorMessage(str);
                        } else {
                            strTrimErrorMessage = null;
                        }
                        if (z && strTrimErrorMessage != null) {
                            i2 = R.string.UnknownError;
                        }
                        final int i7 = i2;
                        if (strTrimErrorMessage == null) {
                            strTrimErrorMessage = LocaleController.getString(i5);
                        }
                        final String str2 = strTrimErrorMessage;
                        if (viewGroup != null) {
                            bulletinFactoryGlobal = BulletinFactory.of((FrameLayout) viewGroup, resourcesProvider);
                        } else if (baseFragment != null) {
                            bulletinFactoryGlobal = BulletinFactory.of(baseFragment);
                        } else {
                            bulletinFactoryGlobal = BulletinFactory.global();
                        }
                        final BulletinFactory bulletinFactory = bulletinFactoryGlobal;
                        if (viewGroup != null) {
                            context = viewGroup.getContext();
                        } else if (baseFragment != null) {
                            context = baseFragment.getContext();
                        }
                        AndroidUtilities.runOnUIThread(new Runnable() {
                            @Override // java.lang.Runnable
                            public final void run() {
                                AiController.$r8$lambda$UrxaUoTrlqMH85byRPLNTm58_Dc(runnable, context, i, bulletinFactory, i7, str2, i4, resourcesProvider);
                            }
                        });
                    }
                    i4 = 8;
                    context = null;
                    if (z) {
                        strTrimErrorMessage = trimErrorMessage(str);
                    } else {
                        strTrimErrorMessage = trimErrorMessage(str);
                    }
                    if (z) {
                        i2 = R.string.UnknownError;
                    }
                    final int i8 = i2;
                    if (strTrimErrorMessage == null) {
                        strTrimErrorMessage = LocaleController.getString(i5);
                    }
                    final CharSequence str3 = strTrimErrorMessage;
                    if (viewGroup != null) {
                        bulletinFactoryGlobal = BulletinFactory.of((FrameLayout) viewGroup, resourcesProvider);
                    } else if (baseFragment != null) {
                        bulletinFactoryGlobal = BulletinFactory.of(baseFragment);
                    } else {
                        bulletinFactoryGlobal = BulletinFactory.global();
                    }
                    final BulletinFactory bulletinFactory2 = bulletinFactoryGlobal;
                    if (viewGroup != null) {
                        context = viewGroup.getContext();
                    } else if (baseFragment != null) {
                        context = baseFragment.getContext();
                    }
                    AndroidUtilities.runOnUIThread(new Runnable() {
                        @Override // java.lang.Runnable
                        public final void run() {
                            AiController.$r8$lambda$UrxaUoTrlqMH85byRPLNTm58_Dc(runnable, context, i, bulletinFactory2, i8, str3, i4, resourcesProvider);
                        }
                    });
                }
                i4 = 5;
                context = null;
                if (z) {
                    strTrimErrorMessage = trimErrorMessage(str);
                } else {
                    strTrimErrorMessage = trimErrorMessage(str);
                }
                if (z) {
                    i2 = R.string.UnknownError;
                }
                final int i9 = i2;
                if (strTrimErrorMessage == null) {
                    strTrimErrorMessage = LocaleController.getString(i5);
                }
                final CharSequence str4 = strTrimErrorMessage;
                if (viewGroup != null) {
                    bulletinFactoryGlobal = BulletinFactory.of((FrameLayout) viewGroup, resourcesProvider);
                } else if (baseFragment != null) {
                    bulletinFactoryGlobal = BulletinFactory.of(baseFragment);
                } else {
                    bulletinFactoryGlobal = BulletinFactory.global();
                }
                final BulletinFactory bulletinFactory3 = bulletinFactoryGlobal;
                if (viewGroup != null) {
                    context = viewGroup.getContext();
                } else if (baseFragment != null) {
                    context = baseFragment.getContext();
                }
                AndroidUtilities.runOnUIThread(new Runnable() {
                    @Override // java.lang.Runnable
                    public final void run() {
                        AiController.$r8$lambda$UrxaUoTrlqMH85byRPLNTm58_Dc(runnable, context, i, bulletinFactory3, i9, str4, i4, resourcesProvider);
                    }
                });
            }
            i4 = i6;
            context = null;
            if (z) {
                strTrimErrorMessage = trimErrorMessage(str);
            } else {
                strTrimErrorMessage = trimErrorMessage(str);
            }
            if (z) {
                i2 = R.string.UnknownError;
            }
            final int i10 = i2;
            if (strTrimErrorMessage == null) {
                strTrimErrorMessage = LocaleController.getString(i5);
            }
            final CharSequence str5 = strTrimErrorMessage;
            if (viewGroup != null) {
                bulletinFactoryGlobal = BulletinFactory.of((FrameLayout) viewGroup, resourcesProvider);
            } else if (baseFragment != null) {
                bulletinFactoryGlobal = BulletinFactory.of(baseFragment);
            } else {
                bulletinFactoryGlobal = BulletinFactory.global();
            }
            final BulletinFactory bulletinFactory4 = bulletinFactoryGlobal;
            if (viewGroup != null) {
                context = viewGroup.getContext();
            } else if (baseFragment != null) {
                context = baseFragment.getContext();
            }
            AndroidUtilities.runOnUIThread(new Runnable() {
                @Override // java.lang.Runnable
                public final void run() {
                    AiController.$r8$lambda$UrxaUoTrlqMH85byRPLNTm58_Dc(runnable, context, i, bulletinFactory4, i10, str5, i4, resourcesProvider);
                }
            });
        }
        i4 = 3;
        i5 = i3;
        context = null;
        if (z) {
            strTrimErrorMessage = trimErrorMessage(str);
        } else {
            strTrimErrorMessage = trimErrorMessage(str);
        }
        if (z) {
            i2 = R.string.UnknownError;
        }
        final int i11 = i2;
        if (strTrimErrorMessage == null) {
            strTrimErrorMessage = LocaleController.getString(i5);
        }
        final CharSequence str6 = strTrimErrorMessage;
        if (viewGroup != null) {
            bulletinFactoryGlobal = BulletinFactory.of((FrameLayout) viewGroup, resourcesProvider);
        } else if (baseFragment != null) {
            bulletinFactoryGlobal = BulletinFactory.of(baseFragment);
        } else {
            bulletinFactoryGlobal = BulletinFactory.global();
        }
        final BulletinFactory bulletinFactory5 = bulletinFactoryGlobal;
        if (viewGroup != null) {
            context = viewGroup.getContext();
        } else if (baseFragment != null) {
            context = baseFragment.getContext();
        }
        AndroidUtilities.runOnUIThread(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                AiController.$r8$lambda$UrxaUoTrlqMH85byRPLNTm58_Dc(runnable, context, i, bulletinFactory5, i11, str6, i4, resourcesProvider);
            }
        });
    }

    public static void $r8$lambda$UrxaUoTrlqMH85byRPLNTm58_Dc(Runnable runnable, Context context, int i, BulletinFactory bulletinFactory, int i2, CharSequence charSequence, int i3, Theme.ResourcesProvider resourcesProvider) {
        if (runnable == null || context == null || !isOnDeviceError(i)) {
            bulletinFactory.createSimpleBulletin(LocaleController.getString(i2), charSequence, i3).show();
            return;
        }
        Bulletin.TwoLineLottieLayout twoLineLottieLayout = new Bulletin.TwoLineLottieLayout(context, resourcesProvider);
        twoLineLottieLayout.setSticker(i3);
        twoLineLottieLayout.titleTextView.setText(LocaleController.getString(i2));
        twoLineLottieLayout.subtitleTextView.setText(charSequence);
        twoLineLottieLayout.setButton(new Bulletin.UndoButton(context, true, resourcesProvider).setText(LocaleController.getString(R.string.AIChangeModel)).setUndoAction(runnable));
        bulletinFactory.create(twoLineLottieLayout, 5000).show();
    }

    private static String trimErrorMessage(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String strTrim = str.trim();
        if (strTrim.isEmpty()) {
            return null;
        }
        if (strTrim.length() <= 160) {
            return strTrim;
        }
        return strTrim.substring(0, 160).trim() + "…";
    }

    public static AiController getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static boolean canUseAI() {
        Service selected = getInstance().getSelected();
        if (selected.isOnDevice()) {
            return OnDeviceAvailability.isReady();
        }
        return !TextUtils.isEmpty(selected.getKey());
    }

    public void loadRoles() {
        ArrayList<Role> roles = AiConfig.getRoles();
        this.roles.clear();
        this.roles.addAll(roles);
        this.roles.removeIf(new Predicate() {
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return AiController.$r8$lambda$ml9V0PZBMdpgTeM8PwcrlItS8AE((Role) obj);
            }
        });
    }

    public static boolean $r8$lambda$ml9V0PZBMdpgTeM8PwcrlItS8AE(Role role) {
        return role == null || role.getName() == null || role.getPrompt() == null;
    }

    public List<Role> getRoles() {
        return Collections.unmodifiableList(this.roles);
    }

    public List<Role> getSuggestedRoles() {
        return (List) Arrays.stream(Suggestions.values()).map(new Function() {
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Suggestions) obj).getRole();
            }
        }).collect(Collectors.toList());
    }

    public boolean isCustomRole(Role role) {
        if (role == null) {
            return false;
        }
        return this.roles.contains(role);
    }

    public boolean isSuggestedRole(Role role) {
        if (role == null) {
            return false;
        }
        return getSuggestedRoles().contains(role);
    }

    public Role getSelectedRole() {
        for (Role role : this.roles) {
            if (role.isSelected()) {
                return role;
            }
        }
        for (Role role2 : getSuggestedRoles()) {
            if (role2.isSelected()) {
                return role2;
            }
        }
        return Suggestions.ASSISTANT.getRole();
    }

    public boolean addRole(Role role) {
        if (isSuggestedRole(role) || isCustomRole(role)) {
            return false;
        }
        this.roles.add(0, role);
        saveRoles();
        return true;
    }

    public boolean removeRole(Role role) {
        if (role == null) {
            return false;
        }
        boolean zRemove = this.roles.remove(role);
        if (zRemove) {
            saveRoles();
        }
        return zRemove;
    }

    public boolean updateRole(Role role, Role role2) {
        int iIndexOf = this.roles.indexOf(role);
        if (iIndexOf == -1) {
            return false;
        }
        if (isSuggestedRole(role2) && !role.equals(role2)) {
            return false;
        }
        this.roles.set(iIndexOf, role2);
        saveRoles();
        return true;
    }

    public void saveRoles() {
        AiConfig.saveRoles(new ArrayList(this.roles));
    }

    public void loadServices() {
        ArrayList<Service> services = AiConfig.getServices();
        this.services.clear();
        this.services.addAll(services);
        this.services.removeIf(new Predicate() {
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((Service) obj).isOnDevice();
            }
        });
        if (OnDeviceAvailability.isSupported()) {
            String modelName = OnDeviceAvailability.getModelName();
            Service service = AiConfig.ON_DEVICE_SERVICE;
            if (TextUtils.isEmpty(modelName)) {
                modelName = "Gemini Nano";
            }
            service.setModel(modelName);
            service.setReasoningEnabled(OnDeviceAvailability.isThinkingSupported() && AiConfig.getOnDeviceReasoning());
            this.services.add(service);
        }
        if (OnDeviceAvailability.isReady() || !AiConfig.ON_DEVICE_SERVICE.getId().equals(AiConfig.getSelectedServiceId())) {
            return;
        }
        AiConfig.setSelectedServiceId(null);
    }

    public List<Service> getAll() {
        return Collections.unmodifiableList(this.services);
    }

    public boolean isServicesEmpty() {
        return this.services.isEmpty();
    }

    public void addService(Service service) {
        if (service == null || service.isOnDevice() || this.services.contains(service)) {
            return;
        }
        this.services.add(service);
        saveServices();
    }

    public void updateService(Service service, Service service2) {
        int iIndexOf;
        if (service == null || service.isOnDevice() || service2 == null || service2.isOnDevice() || (iIndexOf = this.services.indexOf(service)) == -1) {
            return;
        }
        service2.setId(service.getId());
        this.services.set(iIndexOf, service2);
        saveServices();
    }

    public boolean removeService(Service service) {
        if (service == null || service.isOnDevice()) {
            return false;
        }
        boolean zRemove = this.services.remove(service);
        if (zRemove) {
            saveServices();
        }
        return zRemove;
    }

    public Service getSelected() {
        Optional<Service> optionalFindFirst = this.services.stream().filter(new Predicate() {
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((Service) obj).isSelected();
            }
        }).findFirst();
        if (optionalFindFirst.isPresent()) {
            return optionalFindFirst.get();
        }
        for (Service service : this.services) {
            if (!service.isOnDevice()) {
                return service;
            }
        }
        return AiConfig.DEFAULT_SERVICE;
    }

    public void saveServices() {
        this.services.sort(Comparator.comparing(new Function() {
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Service) obj).getModel();
            }
        }, Comparator.nullsLast(Comparator.naturalOrder())));
        ArrayList arrayList = new ArrayList();
        for (Service service : this.services) {
            if (!service.isOnDevice()) {
                arrayList.add(service);
            }
        }
        AiConfig.saveServices(arrayList);
    }

    public static boolean canSendImage(MessageObject messageObject) {
        return messageObject != null && canSendImage(ChatUtils.getInstance().getPathToMessage(messageObject));
    }

    public static boolean canSendImage(String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            return false;
        }
        String lowerCase = str.toLowerCase(Locale.ROOT);
        return lowerCase.endsWith(".png") || lowerCase.endsWith(".jpg") || lowerCase.endsWith(".jpeg") || lowerCase.endsWith(".webp") || lowerCase.endsWith(".heic") || lowerCase.endsWith(".heif");
    }
}
